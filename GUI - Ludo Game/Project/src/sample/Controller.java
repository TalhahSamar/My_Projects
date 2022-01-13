package sample;

import java.util.*;
import javafx.util.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;

public class Controller extends Main{

    public static int [][] initial_position = new int [][] {{90,170,430,510},{150,242,482,574}};
    public static int [][] final_position = new int [][] {{87, 119, 148, 177, 208, 238, 269, 269, 269, 269, 269,
                                                           269, 301, 331, 331, 331, 331, 331, 331, 362, 392, 422,
                                                           451, 482, 513, 513, 513, 482, 451, 422, 392, 362, 331,
                                                           331, 331, 331, 331, 331, 301, 269, 269, 269, 269, 269,
                                                           269, 238, 208, 177, 148, 119, 87, 87},

                                                           {332, 332, 332, 332, 332, 332, 302, 272, 241, 211, 183,
                                                            151, 151, 151, 183, 211, 241, 272, 302, 332, 332, 332,
                                                            332, 332, 332, 362, 393, 393, 393, 393, 393, 393, 427,
                                                            459, 487, 516, 547, 577, 577, 577, 547, 516, 487, 459,
                                                            427, 393, 393, 393, 393, 393, 393, 362}};

    public static boolean w;
    public static int w1;
    public static String w2;
    public static Token[] P = new Token[4];
    public static Button[] db = new Button[4];
    public static int X=0;
    public static int cp=-1;
    Random r = new Random();

    @FXML private AnchorPane Window_1;
    @FXML private AnchorPane Window_2;
    @FXML private Rectangle Polygon_1;
    @FXML private Button CubeDice;
    @FXML private Button ToStart;
    @FXML private Label LabelStart;
    @FXML private Button Token_1;
    @FXML private Button Token_2;
    @FXML private Button Token_3;
    @FXML private Button Token_4;
    @FXML private CheckBox P1Check;
    @FXML private Label P1Label;
    @FXML private TextField P1Name;
    @FXML private CheckBox P2Check;
    @FXML private Label P2Label;
    @FXML private TextField P2Name;
    @FXML private Circle P1T1;
    @FXML private Circle P1T2;
    @FXML private Circle P1T3;
    @FXML private Circle P1T4;
    @FXML private Circle P2T1;
    @FXML private Circle P2T2;
    @FXML private Circle P2T3;
    @FXML private Circle P2T4;

    public void Ludo_Start(){
        cp=-1;
        int value = 1;
        String tc;
        String tn;
        if(P1Check.isSelected()){
            if(cp<0) cp=0;
            tc = "#FF0000";
            if(P1Name.getText().equals("")){
                tn = "Player " + value;
                value++;
            }
            else tn = P1Name.getText();
            P[0] = new Token(tn,tc);
            P[0].token[0] = P1T1;
            P[0].token[1] = P1T2;
            P[0].token[2] = P1T3;
            P[0].token[3] = P1T4;
            for(int i = 0; i<4; i++){
                P[0].token[i].setFill(Paint.valueOf(P[0].tc));
            }
            for(int i=0; i<4; i++){
                P[0].arr2[0][i] = (int)P[0].token[i].getLayoutX()-13;
                P[0].arr2[1][i] = (int)P[0].token[i].getLayoutY()-13;
            }
            for(int i=0; i<4; i++){
                P[0].b2[i] = false;
            }
            for(int i=0; i<4; i++){
                P[0].b1[i] = false;
            }
            P[0].p=1;
            P[0].x=0;
            P[0].y=1;
            P1Label.setText(P[0].tn);
            P1Label.setTextFill(Paint.valueOf(P[0].tc));
        };
        if(P2Check.isSelected()) {
            if (cp<0) cp=3;
            tc = "#FFFF00";
            if(P2Name.getText().equals("")) {
                tn = "Player " + value;
                value++;
            }
            else tn = P2Name.getText();
            P[3] = new Token(tn,tc);
            P[3].token[0] = P2T1;
            P[3].token[1] = P2T2;
            P[3].token[2] = P2T3;
            P[3].token[3] = P2T4;
            for(int i=0;i<4;i++){
                P[3].token[i].setFill(Paint.valueOf(P[3].tc));
            }
            for(int i=0; i<4; i++){
                P[3].arr2[0][i]=(int)P[3].token[i].getLayoutX()-13;
                P[3].arr2[1][i]=(int)P[3].token[i].getLayoutY()-13;
            }
            for(int i=0; i<4; i++){
                P[3].b2[i]=false;
            }
            for(int i=0; i<4; i++){
                P[3].b1[i]=false;
            }
            P[3].p=27;
            P[3].x=0;
            P[3].y=-1;
            P2Label.setText(P[3].tn);
            P2Label.setTextFill(Paint.valueOf(P[3].tc));
        };
        Polygon_1.setFill(Paint.valueOf(P[cp].tc));
        db[0] = Token_1;
        db[1] = Token_2;
        db[2] = Token_3;
        db[3] = Token_4;
        Dice_Change(CubeDice,6);
        w1=1;
        w2="";
        Window_2.setVisible(false);
        Window_1.setVisible(true);
    }

    public void Dice_Roll(){
        w = false;
        X = Dice_Random();
        Dice_Change(CubeDice,X);
        int z = Return(P[cp]);
        if(X==6){
            int k=SeeButton(X,P[cp],db);
            if(k>0){
                See_BT(db);
                if(P[cp].b2[k-1]) w=Go(P[cp],k-1,X);
                else{
                    w=Go(P[cp],k-1,P[cp].p);
                    P[cp].b2[k-1]=true;
                }
            }
            else if(k!=0){
                CubeDice.setDisable(true);
            }
            return;
        }
        else if(z==1){
            int y = Live();
            w=Go(P[cp],y,X);
        }
        else if(z>1){
            int k=SeeButton(X,P[cp],db);
            if(k>0){
                See_BT(db);
                w=Go(P[cp],k-1,X);
            }
            else if(k!=0){
                CubeDice.setDisable(true);
                return;
            }
        }
        if(w){
            Game_Won();
            P[cp].won=true;
            w1++;
            return;
        }
        Change_Pl();
    }

    public int Dice_Random(){
        int val = r.nextInt(6)+1;
        return val;
    }

    public void Dice_Change(Button B,int x) {
        B.setStyle("-fx-background-image: url(Images/Value_"+x+".png)");
        B.setText(""+x);
    }

    public boolean Go(Token P, int t, int x){
        boolean wr = false;
        P.arr1[t]=P.arr1[t]+x;
        if(P.arr1[t]>51) P.arr1[t]=P.arr1[t]-52;

        if(!P.b1[t]){
            int T = P.p-7;
            if(T<0){
                T=T+51;
            }
            if(P.p==1){
                if(P.arr1[t]>T){
                    P.b1[t]=true;
                }
            }
            else if(P.arr1[t]>T && P.arr1[t]<P.p){
                P.b1[t]=true;
            }
            P.token[t].setLayoutX(final_position[0][P.arr1[t]]);
            P.token[t].setLayoutY(final_position[1][P.arr1[t]]);
            Take(t);
        }
        else{
            if(P.arr1[t]>P.p) {
                if(P.arr1[t]>P.p && P.arr1[t]<P.p+6){
                    P.token[t].setLayoutX(final_position[0][P.arr1[t]-1]+30*P.x);
                    P.token[t].setLayoutY(final_position[1][P.arr1[t]-1]+30*P.y);
                }
                else if(P.arr1[t]==P.p+6){
                    P.token[t].setLayoutX(final_position[0][P.arr1[t]-1]+60*P.x);
                    P.token[t].setLayoutY(final_position[1][P.arr1[t]-1]+60*P.y);
                    P.b3[t]=true;
                    wr=TrueWin(P);
                    P.b2[t]=false;
                }
                else if(P.arr1[t]>P.p+6 && P.arr1[t]<P.p+12){
                    P.arr1[t]=P.arr1[t]-x;
                }
                else{
                    P.token[t].setLayoutX(final_position[0][P.arr1[t]]);
                    P.token[t].setLayoutY(final_position[1][P.arr1[t]]);
                    Take(t);
                }
            }
            else{
                P.token[t].setLayoutX(final_position[0][P.arr1[t]]);
                P.token[t].setLayoutY(final_position[1][P.arr1[t]]);
                Take(t);
            }
        }
        return wr;
    }

    public void Box(){
        int i=0;
        if(P1Check.isSelected()) i++;
        if(P2Check.isSelected()) i++;
        if(i<2){
            TranslateTransition tarns1 = new TranslateTransition();
            FadeTransition trans2 = new FadeTransition();
            ParallelTransition trans3 = new ParallelTransition(tarns1,trans2);
            tarns1.setDuration(Duration.seconds(1));
            tarns1.setNode(LabelStart);
            tarns1.setToY(-25);
            trans2.setNode(LabelStart);
            trans2.setDuration(Duration.seconds(1));
            trans2.setFromValue(0.0);
            trans2.setToValue(0.95);
            trans3.play();
            ToStart.setDisable(true);
        }
        else{
            TranslateTransition trans4 = new TranslateTransition();
            trans4.setDuration(Duration.seconds(1));
            trans4.setNode(LabelStart);
            trans4.setToY(25);
            trans4.play();
            ToStart.setDisable(false);
        }
    }

    public void Back(){
        for(int i=0; i<3; i+=2){
            for(int j=0; j<3; j+=2){
                if(P[i]!=null) P[i].token[j].setLayoutX(initial_position[0][0]);
            }
            for(int j=1; j<4; j+=2){
                if(P[i]!=null) P[i].token[j].setLayoutX(initial_position[0][1]);
            }
        }
        for(int i=1; i<4; i+=2) {
            for(int j=0; j<3; j+=2){
                if(P[i]!=null) P[i].token[j].setLayoutX(initial_position[0][2]);
            }
            for(int j=1; j<4; j+=2){
                if(P[i]!=null) P[i].token[j].setLayoutX(initial_position[0][3]);
            }
        }
        for(int i=0;i<2;i++) {
            for(int j=0; j<2; j++){
                if(P[i]!=null) P[i].token[j].setLayoutY(initial_position[1][0]);
            }
            for(int j=2; j<4; j++){
                if(P[i]!=null) P[i].token[j].setLayoutY(initial_position[1][1]);
            }
        }
        for(int i=2;i<4;i++) {
            for(int j=0; j<2; j++){
                if(P[i]!=null) P[i].token[j].setLayoutY(initial_position[1][2]);
            }
            for(int j=2; j<4; j++){
                if(P[i]!=null) P[i].token[j].setLayoutY(initial_position[1][3]);
            }
        }
        for(int i=0; i<4; i++){
            if(P[i]!=null){
                for(int j=0; j<4; j++){
                    P[i].token[j].setFill(Paint.valueOf("#ffffff"));
                }
            }
        }
        for(int i=0;i<4;i++){
            P[i]=null;
        }
        P1Label.setTextFill(Paint.valueOf("#ffffff"));
        P2Label.setTextFill(Paint.valueOf("#ffffff"));
        for(int i=0;i<4;i++){
            P[i]=null;
        }
        CubeDice.setDisable(false);
        See_BT(db);
        Window_2.setVisible(true);
        Window_1.setVisible(false);
    }

    public boolean Give(int x, int y){
        if(!P[x].b3[y] && P[x].arr1[y]>P[x].p && P[x].arr1[y]<P[x].p+6) return false;
        return true;
    }

    public boolean Pos(int x){
        if(x == 1 || x == 14 || x == 27 || x == 40) return true;
        return false;
    }

    public void Restart(int x, int y){
        P[x].token[y].setLayoutX(P[x].arr2[0][y]+13);
        P[x].token[y].setLayoutY(P[x].arr2[1][y]+13);
        P[x].b1[y] = false;
        P[x].b2[y] = false;
        P[x].arr1[y]=0;
    }

    public void Change_Pl(){
        while(true){
            if(cp==0) cp=1;
            else if(cp==1) cp=3;
            else if(cp==2) cp=0;
            else if(cp==3) cp=2;
            if(P[cp]!=null && !P[cp].won){
                Polygon_1.setFill(Paint.valueOf(P[cp].tc));
                return;
            }
        }
    }

    public int Live() {
        for(int i=0; i<4; i++){
            if(P[cp].b2[i]) return i;
        }
        return -1;
    }

    public int Return(Token Pl) {
        int r = 0;
        for(int i=0; i<4; i++){
            if(Pl.b2[i]==true) r++;
        }
        return r;
    }

    public void Click(Event Ev){
        int x = Integer.parseInt(((Button)Ev.getSource()).getText());
        int y =	Integer.parseInt(CubeDice.getText());
        if(P[cp].b2[x]) w=Go(P[cp],x,y);
        else{
            w=Go(P[cp],x,P[cp].p);
            P[cp].b2[x]=true;
        }
        See_BT(db);
        CubeDice.setDisable(false);
        if(w){
            Game_Won();
            P[cp]=null;
            w1++;
        }
        if(y==6) return;
        Change_Pl();
    }

    public int SeeButton(int d, Token P, Button[] db){
        int k=0;
        if(d==6){
            for(int i=0; i<4; i++) {
                if(!P.b2[i]){
                    if(!P.b3[i]){
                        db[i].setLayoutX(P.arr2[0][i]);
                        db[i].setLayoutY(P.arr2[1][i]);
                        db[i].setVisible(true);
                        if(k==0) k=i+1;
                        else k=-1;
                    }
                }
                else if(!(P.b1[i] && (P.arr1[i]>P.p && P.arr1[i]<P.p+6))){
                    db[i].setLayoutX(final_position[0][P.arr1[i]]-13);
                    db[i].setLayoutY(final_position[1][P.arr1[i]]-13);
                    db[i].setVisible(true);
                    if(k==0) k=i+1;
                    else k=-1;
                }
            }
        }
        else{
            for(int i=0;i<4;i++){
                if(P.b2[i]){
                    if(!P.b1[i]){
                        db[i].setLayoutX(final_position[0][P.arr1[i]]-13);
                        db[i].setLayoutY(final_position[1][P.arr1[i]]-13);
                        db[i].setVisible(true);
                        if(k==0) k=i+1;
                        else k=-1;
                    }
                    else if(P.arr1[i]>P.p && P.arr1[i]<P.p+6){
                        if(P.arr1[i]+d<=P.p+6){
                            db[i].setLayoutX((final_position[0][P.arr1[i]-1]+30*P.x)-13);
                            db[i].setLayoutY((final_position[1][P.arr1[i]-1]+30*P.y)-13);
                            db[i].setVisible(true);
                            if(k==0) k=i+1;
                            else k=-1;
                        }
                    }
                    else{
                        db[i].setLayoutX(final_position[0][P.arr1[i]]-13);
                        db[i].setLayoutY(final_position[1][P.arr1[i]]-13);
                        db[i].setVisible(true);
                        if(k==0) k=i+1;
                        else k=-1;
                    }
                }
            }
        }
        return k;
    }

    public void See_BT(Button[] db){
        for(int i=0; i<4; i++){
            db[i].setVisible(false);
        }
    }

    public void Take(int t){
        int c = cp;
        if(!Pos(P[c].arr1[t])){
            ArrayList<Integer> arr = Position(c,t);
            if(!arr.isEmpty()){
                for(int i=0; i<arr.size(); i=i+2){
                    int x = arr.get(i);
                    int y = arr.get(i+1);
                    Restart(x, y);
                }

            }
        }
    }

    public ArrayList<Integer> Position(int x, int y){
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0;i<4;i++){
            if(i!=x && P[i]!=null){
                for(int k=0;k<4;k++){
                    if(P[x].arr1[y]==P[i].arr1[k] && P[i].b2[k] && Give(i,k)){
                        arr.add(i);
                        arr.add(k);
                    }
                }
            }
        }
        return arr;
    }

    public boolean TrueWin(Token P){
        for(int i=0;i<4;i++){
            if(!P.b3[i]){
                return false;
            }
        }
        return true;
    }

    public void Finish(int a, ImageView img1, ImageView img2, Label lb){
        CubeDice.setDisable(true);
        int x, y;
        if (a % 2 == 0) {
            x=0;
            if(a<1) y=0;
            else y=2;
        }
        else {
            x=2;
            if(a<2) y=0;
            else y=2;
        }
        img1.setLayoutX(initial_position[0][x]-45);
        img1.setLayoutY(initial_position[1][y]-43);
        Win_AS(img1, 1, 1.3);
        img2.setLayoutX(50);
        img2.setLayoutY(800);
        TranslateTransition trans1 = new TranslateTransition();
        trans1.setDuration(Duration.seconds(1));
        trans1.setNode(img2);
        trans1.setToY(-700);
        TranslateTransition trans2 = new TranslateTransition();
        trans2.setDuration(Duration.seconds(.5));
        trans2.setNode(img2);
        trans2.setToX(initial_position[0][x]-260);
        trans2.setToY(initial_position[1][y]-1018);
        ScaleTransition trans3 = new ScaleTransition();
        trans3.setDuration(Duration.seconds(.5));
        trans3.setNode(img2);
        trans3.setToX(0.35);
        trans3.setToY(0.35);
        RotateTransition trans4= new RotateTransition();
        trans4.setDuration(Duration.seconds(.5));
        trans4.setNode(img2);
        trans4.setByAngle((a % 2==0)?-360:360);
        lb.setLayoutX(initial_position[0][x]+13);
        lb.setLayoutY(initial_position[1][y]-15);
        TranslateTransition trans5 = new TranslateTransition();
        trans5.setDuration(Duration.seconds(1));
        trans5.setNode(lb);
        trans5.setToY(-10);
        FadeTransition trans6 = new FadeTransition();
        trans6.setNode(lb);
        trans6.setDuration(Duration.seconds(.7));
        trans6.setFromValue(0.0);
        trans6.setToValue(0.95);
        ParallelTransition trans7 = new ParallelTransition(trans2,trans3,trans4);
        ParallelTransition trans8 = new ParallelTransition(trans5,trans6);
        SequentialTransition trans9 = new SequentialTransition(trans1,trans7,trans8);
        trans9.play();
        trans9.setOnFinished((e)->{
            CubeDice.setDisable(false);
            int k=0;
            for(int i=0; i<4; i++){
                if(P[i]==null || P[i].won){
                    k++;
                }
            }
            if(k==3) Out();
            else Change_Pl();
        });
    }

    public void Win_AS(ImageView img, int x, double y){
        Animation p = new Board(Duration.millis(1000), 12, 10, 0, 0, 170, 170);
        img.setVisible(true);
        img.setDisable(true);
        ((Board)p).Pic(img);
        p.setCycleCount(x);
        p.setDelay(Duration.seconds(y));
        p.play();
    }

    public void Game_Won(){
        w2 = w2 + "\n" + "  " + P[cp].tn;
        ImageView i1 = new ImageView(new Image("Images/Blank.png"));
        i1.setId("toDelet");
        i1.setViewport(new Rectangle2D(0, 0, 170, 170));
        Label l1 = new Label(""+w1);
        l1.setId("toDelet");
        l1.getStyleClass().add("Winning");
        l1.setTextFill(Paint.valueOf(P[cp].tc));
        Window_1.getChildren().add(i1);
        Window_1.getChildren().add(l1);
        i1.setLayoutX(50);
        i1.setLayoutY(800);
        Finish(cp, i1, i1, l1);
    }

    public void Out(){
        Alert l = new Alert(Alert.AlertType.INFORMATION);
        l.setHeaderText("**WINNER**"+w2);
        l.show();
        l.setOnCloseRequest((e)->{
            Back();
            for(int i=0;i<9;i++) {
                Node N = Window_1.lookup("#toDelet");
                if(N!=null){
                    N.setId("dead");
                    N.setVisible(false);
                    N=null;
                }
                else return;
            }
        });
    }
}

class Token{
    boolean won;
    int p;
    int x;
    int y;
    boolean[] b1 = new boolean[4];
    boolean[] b2 = new boolean[4];
    boolean[] b3 = new boolean[4];
    int[] arr1 = new int[4];
    int[][] arr2 = new int[2][4];
    Circle[] token = new Circle[4];

    double d;
    String tn;
    String tc;

    Token(String tn, String tc){
        this.tn=tn;
        this.tc=tc;
        won=false;
        d=0;
        for(int i=0; i<4; i++){
            arr1[i]=0;
        }
    }
}

class Board extends Transition{

    private ImageView vi;
    private final int i1;
    private final int i2;
    private final int x;
    private final int y;
    private final int l1;
    private final int l2;
    private int value;

    public Board(Duration t, int i1, int i2, int x, int y, int l1, int l2){
        this.i1 = i1;
        this.i2 = i2;
        this.x = x;
        this.y = y;
        this.l1 = l1;
        this.l2 = l2;
        setCycleDuration(t);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void Pic(ImageView vi) {
        this.vi = vi;
    }

    protected void interpolate(double k) {
        final int value2 = Math.min((int) Math.floor(k * i1), i1 - 1);
        if (value2 != value) {
            final int x1 = (value2 % i2) * l1  + x;
            final int y1 = (value2 / i2) * l2 + y;
            vi.setViewport(new Rectangle2D(x1, y1, l1, l2));
            value = value2;
        }
    }
}