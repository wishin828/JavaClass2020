/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.model;

import demos.db.Product;
import java.util.List;
import javax.ejb.Remote;
import javax.validation.Valid;

/**
 *
 * @author Admin
 */
@Remote
public interface ProductFacadeRemote {
// business method definitions will be added here
    void create(@Valid Product product);
    void update(@Valid Product product);
    void delete(Product product);
    Product findProduct(Integer id);
    List<Product> findProductByName(String name);
}
