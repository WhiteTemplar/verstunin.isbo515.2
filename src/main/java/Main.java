import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 10.02.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MyHashTable t = new MyHashTable(100);
        t.put("table", "стол");
        String r = t.get("table");
        System.out.println(r);
        System.out.println(run(100));
        XYSeries s = new XYSeries("1");
        int[] ns = {100, 500, 1000};
        for (int n : ns) {
            long time = run(n);
            s.add(n, time);
        }
        XYSeriesCollection c = new XYSeriesCollection(s);
        JFreeChart ch = ChartFactory.createXYLineChart("Hash", "n", "t",c);
        File f= new File("Graph.png");
        BufferedImage im=ch.createBufferedImage(800,600);
        ImageIO.write(im,"png",f);
        Desktop.getDesktop().open(f);
    }
    static long run(int n) {
        long t0 = System.currentTimeMillis();
        MyHashTable t = new MyHashTable(n);
        int c = 10000;
        for (int i = 0; i < c; i++) {
            t.put("table" + i, "стол" + i);
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < c; j++) {
                t.get("table" + j);
            }
        }
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }

}

