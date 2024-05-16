package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.components;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.ProductEditRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class ProductComponent extends VBox {
    static void editProduct(Product product) {
            System.out.println(product.getId());
        Router.go("products.edit", new ProductEditRequest(product.getId()));

    }

    private EventHandler<MouseEvent> createMouseClickedHandler(Product product) {
        return event -> {
            if (event.getSource() instanceof ProductComponent) {
                // Handle the event only if the source component is of the desired type
                editProduct(product);
            }
        };
    }


    public ProductComponent(Product product) throws FileNotFoundException {
        this.setPrefWidth(224);
        this.setStyle("-fx-background-color: #fff");
        this.setPadding(new Insets(8, 8, 8, 8));
        this.getChildren().add(new ImageComponent("/images/products/default.png", 208, 208));
        this.getChildren().add(getTitleText(product.getName()));
        this.getChildren().add(new Text("stoc: " + product.getStock()));
        this.getChildren().add(getFooter("153.24", product.getId()));
        this.setOnMouseClicked(createMouseClickedHandler(product));
    }

    private static Text getTitleText(String name) {
        Text text = new Text(name);

        text.setWrappingWidth(205);

        return text;
    }

    private static HBox getFooter(String price, Integer id) {
        HBox container = new HBox();
        container.setAlignment(Pos.BOTTOM_RIGHT);

        Text text = new Text(price + " Lei");
        text.setWrappingWidth(175);
        text.setFill(Color.web("#ed1c24"));
        text.setFont(Font.font("System", FontWeight.BOLD, 14));


        ImageView imageView = null;
        try {
            imageView = new ImageComponent("/images/products/buy.png", 18, 18);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: #005EB8;");
        button.setPadding(new Insets(9));

        button.setOnAction(event -> {
            Router.go("orders.create");
        });

        container.getChildren().addAll(text, button);

        return container;
    }
}
