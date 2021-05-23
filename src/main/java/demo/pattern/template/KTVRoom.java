package demo.pattern.template;

/**
 * @author 张烈文
 */
public abstract class KTVRoom {

    public void procedure() {
        openDevice();
        orderSong();
        orderExtra();
        pay();
    }


//    用户根据自身选择来是否需要实现该方法
    protected void orderExtra() {}

    //    子类必须实现的方法，必须得选歌
    protected abstract void orderSong();


    //    模板方法自带方法，进Ktv必须要做的事请
    private void openDevice() {
        System.out.println("服务员打开视频和音响");
    }

//    模板方法自带方法，进Ktv必须要做的事请
    private void pay() {
        System.out.println("用户结账");
    }


}
