package com.mruruc.controller;

import com.mruruc.model.Product;
import com.mruruc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/products";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/products-by-price-range")
    public String getAllProductsByPriceRange(@RequestParam("minPrice") Double minPrice,
                                             @RequestParam("maxPrice") Double maxPrice, Model model) {
        List<Product> allProducts = productService.getAllProducts().stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .toList();
        model.addAttribute("products", allProducts);
        return "product/products";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/product-details")
    public String getProductDetails(@RequestParam("productId") Long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product/product-details";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit-product")
    public String getProductDetailsForEditing(@RequestParam("productId") Long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product/edit-product";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/edit-product")
    public String editProductDetail(@RequestParam("productId") Long productId, @ModelAttribute Product product, Model model) {
        try {
            System.out.println("Product ID: " + productId);
            System.out.println("inside put Product: " + product);
            model.addAttribute("product", productService.updateProduct(productId, product));
            return "redirect:/edit-product?productId=" + productId;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:./edit-product?productId=" + productId;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-product")
    public String addProduct(@ModelAttribute Product product, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("errorMap", productValidator.getErrorMap(result));
//            model.addAttribute("product", product);
//            return "product/new-product";
//        }

        //model.addAttribute("product", savedProduct);
        System.out.println(product);
        return "redirect:./product-details?productId=" + productService.addProduct(product);
    }

}
