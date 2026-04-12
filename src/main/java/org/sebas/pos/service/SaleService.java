package org.sebas.pos.service;

import org.sebas.pos.exception.CartEmptyExcepcion;
import org.sebas.pos.exception.NoStockException;
import org.sebas.pos.exception.ResourceNotFoundException;
import org.sebas.pos.model.PAYMENT;
import org.sebas.pos.model.Product;
import org.sebas.pos.model.SaleItem;
import org.sebas.pos.model.Sales;
import org.sebas.pos.repo.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService {

    private final SalesRepo salesRepo;
    private final ProductService productService;

    @Autowired
    public SaleService(ProductService productService, SalesRepo salesRepo) {
        this.productService = productService;
        this.salesRepo = salesRepo;
    }

    @Transactional
    public Sales makeSale(List<SaleItem> products) {
        if (products == null || products.isEmpty()){
            throw new CartEmptyExcepcion("The cart is empty");
        }

        Sales sale = new Sales();

        // initialize
        sale.setProductsList(new ArrayList<>());
        sale.setPayment_type(PAYMENT.CARD);

        BigDecimal totalPrice = BigDecimal.ZERO;
        int totalProducts = 0;

        for (SaleItem item : products) {
            if (item == null) {
                continue; // skip null entries
            }

            if (item.getProduct() == null || item.getProduct().getId() == null) {
                throw new IllegalArgumentException("Each SaleItem must contain a product with an id");
            }

            Integer qty = item.getQuantity();
            if (qty == null || qty <= 0) {
                throw new IllegalArgumentException("Each SaleItem must contain a quantity > 0");
            }

            // Load managed product from DB
            UUID id_item = item.getProduct().getId();
            Product product = productService.findEntityById(id_item);

            Integer stock = product.getStock() == null ? 0 : product.getStock();
            if (stock <= 0) {
                throw new NoStockException("The product " + product.getProductName() + " stock is 0");
            }
            if (stock < qty) {
                throw new NoStockException("Insufficient stock for product " + product.getProductName());
            }

            // Set product to the item
            item.setProduct(product);

            // link item to sale
            item.setSale(sale);
            sale.getProductsList().add(item);

            // calculate item total
            if (product.getPrice() == null) {
                throw new IllegalArgumentException("Product price missing for " + product.getProductName());
            }
            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(qty));
            totalPrice = totalPrice.add(itemTotal);

            totalProducts += qty;

            // update and save product stock
            product.setStock(stock - qty);
            productService.updateEasyProduct(product);
        }

        sale.setTotalProducts(totalProducts);

        // apply IVA
        BigDecimal ivaPercent = BigDecimal.valueOf(sale.getIvaPorcentage()).divide(BigDecimal.valueOf(100));
        BigDecimal totalWithIva = totalPrice.multiply(BigDecimal.ONE.add(ivaPercent));
        sale.setTotalPrice(totalWithIva);

        // save sale (cascade should persist items)
        return salesRepo.save(sale);
    }


    public List<Sales> getSalesHistory() {
        return salesRepo.findAll();
    }

    public Sales getSaleTicket(Long id) {


        Optional<Sales> salesOptional = salesRepo.findById(id);
        if (salesOptional.isEmpty()) {
            throw new ResourceNotFoundException("The sale is not in the database");
        }
        return salesOptional.get();
    }
}
