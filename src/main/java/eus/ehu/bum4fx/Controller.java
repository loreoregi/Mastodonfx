package eus.ehu.bum4fx;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import eus.ehu.bum4fx.domain.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField authorName;

    @FXML
    private CheckBox boosted;

    @FXML
    private TextField date;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    @FXML
    private WebView tootContent;

    private String body;

    private Gson gson;

    private JsonArray jsonArray;

    private Type statusList;

    private List<Status> list;

    private int tootIndex = 0;

    private void showToot(){
        Status status = list.get(tootIndex);
        if(status.getReblog() != null)
            boosted.setSelected(true);
        else
            boosted.setSelected(false);

        date.setText(status.getDate());
        tootContent.getEngine().loadContent(status.getContent());
        authorName.setText(status.getAuthorName());
    }


    @FXML
    void initialize() {
        String id = "109897230504677704";
        body = Utils.request("accounts/"+id+"/statuses");
        gson = new Gson();
        jsonArray = gson.fromJson(body, JsonArray.class);
        statusList= new TypeToken<ArrayList<Status>>() {}.getType();
        list = new ArrayList<Status>(gson.fromJson(jsonArray.getAsJsonArray(), statusList));
        showToot();

    }

}
