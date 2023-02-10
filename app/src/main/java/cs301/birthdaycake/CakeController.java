package cs301.birthdaycake;

public class CakeController{

    private CakeView initCakeView;
    private CakeModel initCakeModel;

    public CakeController(CakeView cakeView) {
        initCakeView = cakeView;
        initCakeModel = cakeView.getCake();
    }


}
