package CaculateMV;

/**
 * 工人实体类
 */
public class Worker {
    /**
     * 工人id
     */
    private int id;
    /**
     * 工人质量
     */
    private double quality;
    /**
     * 工人薪水
     */
    private double cost;

    public Worker(int id, double quality, double cost) {
        this.id = id;
        this.quality = quality;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", quality=" + quality +
                '}';
    }
}