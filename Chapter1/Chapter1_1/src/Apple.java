public class Apple {
    private String colour;
    private Integer weight;
    public Apple(String colour,Integer weight){
        this.colour = colour;
        this.weight = weight;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String toString(){
        return this.colour+ "--"+this.weight;
    }

    public static boolean isGreenApple(Apple a) {
        return Common.GREEN.equals(a.getColour());
    }

    public static boolean isHeavyApple(Apple a) {
        return a.getWeight() > 100;
    }
}
