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
         
         int count = 0;

         try (PrintWriter writer = new PrintWriter(new File("OutputImageRGB.csv"))) {
         StringBuilder sb = new StringBuilder();
            for(int i=0; i<height; i++) {
            
               for(int j=0; j<width; j++) {
               
                  
                  Color c = new Color(image.getRGB(j, i));

                  sb.append("[" + c.getRed() + "." + c.getGreen() + "." + c.getBlue()+ "]");

                  System.out.println("S.height: " + i + " S.width:"+ j + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
                  
                  sb.append(',');
                     
               }
               sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
         } catch (FileNotFoundException e) {
                     System.out.println(e.getMessage());
         }
      } catch (Exception e) {}
   }
   




   static public void main(String args[]) throws Exception {
      Pixel obj = new Pixel();
   }


}