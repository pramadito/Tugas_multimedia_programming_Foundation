import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



class Pixel {
   BufferedImage image;
   int width;
   int height;
   
   public Pixel() {
      try {
         File input = new File("InputImage.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         
         double cf = 0;
         double mf = 0;
         double yk = 0;


         try (PrintWriter writer = new PrintWriter(new File("OutputImageRGB.csv"))) {
         StringBuilder sb = new StringBuilder();
            for(int i=0; i<height; i++) {
            
               for(int j=0; j<width; j++) {
               
                  
                  Color c = new Color(image.getRGB(j, i));

                  sb.append("[" + c.getRed() + "." + c.getGreen() + "." + c.getBlue()+ "]");

                  
                  
                  sb.append(',');
                     
               }
               sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("Done RGB!");
         } catch (FileNotFoundException e) {
                     System.out.println(e.getMessage());

         }


         //for cymk

         try (PrintWriter writer = new PrintWriter(new File("OutputImageCMYK.csv"))) {
         StringBuilder sb = new StringBuilder();
            for(int i=0; i<height; i++) {
            
               for(int j=0; j<width; j++) {
               
                  
                  Color c = new Color(image.getRGB(j, i));

                  double red = c.getRed() ;
                  double green = c.getGreen();
                  double blue = c.getBlue();
                  double red1 = red / 255;
                  double green1 = green / 255;
                  double blue1 = blue / 255;
                  double max = (Math.max(Math.max(red1, green1), blue1));
                  double K = 1 - max;
                  double C = (1 - red1 - K) / (1 - K);
                  double M = (1 - green1 - K) / (1 - K);
                  double Y = (1 - blue1 - K) / (1 - K);
                  double CMYK[] = {C, M, Y, K};
                  String cmyk = "[" + Math.round(C*100.0)/100.0 + " . " + Math.round(M *100.0)/100.0 + " . " + Math.round(Y*100.0)/100.0 + " . " + Math.round(K*100.0)/100.0 + "]";


                  sb.append(cmyk);
                  sb.append(',');
                     
               }
               sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("Done CMYK!");
         } catch (FileNotFoundException e) {
                     System.out.println(e.getMessage());
         }






      } catch (Exception e) {}
   }
   



    
    
    
    
   



   static public void main(String args[]) throws Exception {
      Pixel obj = new Pixel();
   }


}