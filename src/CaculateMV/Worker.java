package CaculateMV;

/**
 * ����ʵ����
 */
public class Worker {
    /**
     * ����id
     */
    private int id;
    /**
     * ��������
     */
    private double quality;
    /**
     * ����нˮ
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