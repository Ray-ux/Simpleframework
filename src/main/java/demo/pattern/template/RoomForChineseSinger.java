package demo.pattern.template;

public class RoomForChineseSinger extends KTVRoom {
    @Override
    protected void orderSong() {
        System.out.println("来一首晴天---周杰伦");
    }
    @Override
    protected void orderExtra() {
        System.out.println("东西很便宜，一样来一件");
    }
}
