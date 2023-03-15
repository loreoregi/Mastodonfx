package eus.ehu.bum4fx;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import eus.ehu.bum4fx.domain.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Controller {

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

    @FXML
    private Label output;
    private String body;

    private Gson gson;

    private JsonArray jsonArray;

    private Type statusList;

    private List<Status> list;

    private int tootIndex = 0;

    private void showToot(){
        Status status = list.get(tootIndex);
        if(status.getReblog() != null){
            boosted.setSelected(true);
            status = status.getReblog();
        }
        else
            boosted.setSelected(false);

        date.setText(status.getDate());
        tootContent.getEngine().loadContent(status.getContent());
        authorName.setText(status.getAuthorName());

    }

    @FXML
    void previousClick(ActionEvent event) {
        if (tootIndex>0){
            tootIndex--;
            showToot();
            output.setText("");
        }
        else
            output.setText("You cannot reach the previous one. You already are in the first toot!");

    }

    @FXML
    void nextClick(ActionEvent event) {
        if (tootIndex<list.size()-1){
            tootIndex++;
            showToot();
            output.setText("");
        }
        else
            output.setText("You cannot reach the next one. You already are in the last toot!");

    }

    @FXML
    void initialize() {
        String id = "109897225227668274";
        body = Utils.request("accounts/"+id+"/statuses");
        gson = new Gson();
        jsonArray = gson.fromJson(body, JsonArray.class);
        statusList= new TypeToken<ArrayList<Status>>() {}.getType();
        list = new ArrayList<Status>(gson.fromJson(jsonArray.getAsJsonArray(), statusList));
        tootContent.getEngine().getLoadWorker().stateProperty().addListener(new HyperLinkRedirectListener(tootContent));
        showToot();

    }

}
