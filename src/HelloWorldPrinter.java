
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
 
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
public class HelloWorldPrinter implements Printable {
	
	static String toprint;
	static String toprintgrade;
	static String overallscore;
	
    public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException {
 
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
 
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);
        g.setFont(newFont);
        g.drawString(CurrentUserInfo.getName() + " 's Report", 10, 90);
        g.setFont(currentFont);
        
        g.drawString("REMARKS:", 430, 30);
        for(int i = 0; i < 12; i++)
        	g.drawString("|", 350, 10+i*20);
        
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy      HH:mm:ss").format(Calendar.getInstance().getTime());
        g.drawString(timeStamp,10,120);
        g.drawString("-------------------------------------------------------------------",10,150);
        g.drawString("Name: " + CurrentUserInfo.getName(), 10, 170);
        g.drawString("Age:  " + CurrentUserInfo.getAge(), 10, 190);
        g.drawString("A-Math Username: " + CurrentUserInfo.getUsername(), 10, 210);
        g.drawString("-------------------------------------------------------------------",10,230);
        
        
        try {
			Image grphimg = ImageIO.read(new File(CurrentUserInfo.getUserFileNameImage()));
			g.drawImage(grphimg,10,250,550,300,null);
		} catch (IOException e) {
			
		}
        
        g.drawString("Overall Average: " + overallscore + " (" + (int) Math.round((Double.parseDouble(overallscore)/8000)*100) + "%)", 10, 570);
        
        String[] lines = toprint.split("\n");
        for(int i = 0; i < lines.length; i++)
        	g.drawString(lines[i], 10, 590+(i*13));
        
        
        
        
        g.drawString("Individual Averages: Average Score / Letter grade", 10, 745);
        g.drawString(toprintgrade, 10, 760);
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
 
    public void printPprGraph() {
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    }
 
    public static void mainmethod(String[] s) {
  
        HelloWorldPrinter tempP = new HelloWorldPrinter();
        toprint = s[0];
        toprintgrade = s[1];
        overallscore = s[2];
        tempP.printPprGraph();
    }
}