package GreedyCalculateMV;

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
    private double salary;

    public Worker(int id, double quality, double salary) {
        this.id = id;
        this.quality = quality;
        this.salary = salary;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", quality=" + quality +
                '}';
    }
}