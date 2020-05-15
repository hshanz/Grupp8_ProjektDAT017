package Project;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.List;


public class BackendControllerProducts {
    private IMatDataHandler iM;
    BackendControllerProducts backendControllerProducts;

    public BackendControllerProducts() {
        iM = IMatDataHandler.getInstance();
    }

    public void shutDown(){
        iM.shutDown();
    }

    public void addFavorite(int id){
        iM.addFavorite(id);
    }

    public void addFavorite(Product p){
        iM.addFavorite(p);
    }

    public List<Product> getFavorites(){
        return iM.favorites();
    }

    public List<Product> getProducts(String str){
        return iM.findProducts(str);
    }

    public Image getFXImage(Product p){
        return iM.getFXImage(p);
    }

    public Image getFXImage(Product p,double w, double h){
        return iM.getFXImage(p,w,h);
    }
    public Image getFXImage(Product p,double w, double h,boolean pRatio){
        return iM.getFXImage(p,w,h,pRatio);
    }
    public List<Order> getOrders(){
        return iM.getOrders();
    }
    public Product getProduct(int id){
        return iM.getProduct(id);
    }
    public List<Product> getProducts(){
        return iM.getProducts();
    }
    public boolean isFavorite(Product p){
        return iM.isFavorite(p);
    }
    public Order placeOrder(){
        return iM.placeOrder();
    }
    public Order placeOrder(boolean clrCart){
        return iM.placeOrder(clrCart);
    }
    public void removeFavorite(Product p){
        iM.removeFavorite(p);
    }

}



