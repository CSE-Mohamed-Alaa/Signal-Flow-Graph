import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.LinkedList;
import java.util.List;

public class DrawController {
    @FXML
    TextArea textArea;
    @FXML
    private Group group;
    static int nodes;
    @FXML
    private Button add;
    @FXML
    private Button calc;
    private int[][] matrix;
    private List[] list;

    static void setNodes(int n) {
        nodes = n;
    }

    public void initialize() {
        for (int i = 0; i < nodes; i++) {
            Circle circle = new Circle(60 * i, 60 * i, 25, Color.WHITE);
            Label number = new Label();
            number.setText(i + "");
            number.setLayoutX(60 * i - 25);
            number.setPrefWidth(50);
            number.setPrefHeight(10);
            number.setLayoutY(60 * i - 10);
            number.setAlignment(Pos.CENTER);
            circle.setStroke(Color.BLACK);
            group.getChildren().add(circle);
            group.getChildren().add(number);
        }
    }

    @FXML
    private void addEdges() {
        String[] s = textArea.getText().split("\n");
        int[][] parameters = new int[s.length][3];
        for (int i = 0; i < s.length; i++) {
            String[] tmp = s[i].replaceAll("\\s+", "/s+").trim().split("/s");
            if (tmp.length != 3) return;
            parameters[i][0] = Integer.parseInt(tmp[0]);
            parameters[i][1] = Integer.parseInt(tmp[1]);
            parameters[i][2] = Integer.parseInt(tmp[2]);
        }
        for (int[] arr : parameters) {
            Arrow arrow;
            if (arr[0] > arr[1]) {
                arrow = new Arrow(60f * arr[1], 60 * arr[1] + 25f + 1, 60f * arr[1], 60 * arr[1] + 25f);
                group.getChildren().add(arrow);

                Path path = new Path();
                MoveTo moveTo = new MoveTo();
                moveTo.setX(60 * arr[0] - 25f);
                moveTo.setY(60f * arr[0]);

                QuadCurveTo quadTo = new QuadCurveTo();
                quadTo.setControlX(60 * arr[1]);
                quadTo.setControlY(60f * arr[0]);
                quadTo.setX(60f * arr[1]);
                quadTo.setY(60 * arr[1] + 25f);

                path.getElements().add(moveTo);
                path.getElements().add(quadTo);

                group.getChildren().add(path);

                Label weight = new Label();
                weight.setText(arr[2] + "");
                weight.setLayoutX(quadTo.getControlX() - (quadTo.getControlX() - quadTo.getX()) * 0.707 - 5);
                weight.setLayoutY(quadTo.getControlY() - (quadTo.getControlY() - quadTo.getY()) * 0.707 - 5);
                group.getChildren().add(weight);

            } else if (arr[0] == arr[1] - 1) {
                arrow = new Arrow(60 * arr[0] + 20, 60 * arr[0] + 20, 60 * arr[1] - 20, 60 * arr[1] - 20);
                group.getChildren().add(arrow);

                Label weight = new Label();
                weight.setText(arr[2] + "");
                weight.setLayoutX((60 * arr[0] + 60 * arr[1]) / 2 + 5);
                weight.setLayoutY((60 * arr[0] + 60 * arr[1]) / 2 - 15);
                group.getChildren().add(weight);
            } else if (arr[0] < arr[1]) {
                arrow = new Arrow(60 * arr[1], 60 * arr[1] - 26, 60 * arr[1], 60 * arr[1] - 25);
                group.getChildren().add(arrow);
                Path path = new Path();
                MoveTo moveTo = new MoveTo();
                moveTo.setX(60 * arr[0] + 25f);
                moveTo.setY(60f * arr[0]);
                QuadCurveTo quadTo = new QuadCurveTo();
                quadTo.setControlX(60 * arr[1]);
                quadTo.setControlY(60f * arr[0]);
                quadTo.setX(60f * arr[1]);
                quadTo.setY(60 * arr[1] - 25f);
                path.getElements().add(moveTo);
                path.getElements().add(quadTo);
                group.getChildren().add(path);

                Label weight = new Label();
                weight.setText(arr[2] + "");
                weight.setLayoutX(quadTo.getControlX() - (quadTo.getControlX() - quadTo.getX()) * 0.707 + 5);
                weight.setLayoutY(quadTo.getControlY() - (quadTo.getControlY() - quadTo.getY()) * 0.707 + 5);
                group.getChildren().add(weight);
            }
        }
        if (matrix == null) {
            matrix = new int[nodes][nodes];
            list = new LinkedList[nodes];
            for (int i = 0; i < nodes; i++) {
                list[i] = new LinkedList();
                matrix[i] = new int[nodes];
            }
        }
        for (int i = 0; i < parameters.length; i++) {
            matrix[parameters[i][0]][parameters[i][1]] = parameters[i][2];
            list[parameters[i][0]].add(parameters[i][1]);
        }
    }

    @FXML
    private void solve() {
        Solver solver = new Solver(matrix, list);
        solver.solve();
    }
}
