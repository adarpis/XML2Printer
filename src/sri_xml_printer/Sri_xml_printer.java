/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri_xml_printer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.FontMetrics;

/**
 *
 * @author aarpi
 */
public class Sri_xml_printer {

    public class BillPrintable implements Printable {

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
		throws PrinterException {

	    int result = NO_SUCH_PAGE;
	    if (pageIndex == 0) {

		Graphics2D g2d = (Graphics2D) graphics;

		double width = pageFormat.getImageableWidth();

		g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

		////////// code by alqama//////////////
		FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));
		//    int idLength=metrics.stringWidth("000000");
		//int idLength=metrics.stringWidth("00");
		int idLength = metrics.stringWidth("000");
		int amtLength = metrics.stringWidth("000000");
		int qtyLength = metrics.stringWidth("00000");
		int priceLength = metrics.stringWidth("000000");
		int prodLength = (int) width - idLength - amtLength - qtyLength - priceLength - 17;

		//    int idPosition=0;
		//    int productPosition=idPosition + idLength + 2;
		//    int pricePosition=productPosition + prodLength +10;
		//    int qtyPosition=pricePosition + priceLength + 2;
		//    int amtPosition=qtyPosition + qtyLength + 2;
		int productPosition = 0;
		int discountPosition = prodLength + 5;
		int pricePosition = discountPosition + idLength + 10;
		int qtyPosition = pricePosition + priceLength + 4;
		int amtPosition = qtyPosition + qtyLength;

		try {
		    /*Draw Header*/
		    int y = 20;
		    int yShift = 10;
		    int headerRectHeight = 15;
		    int headerRectHeighta = 40;

		    ///////////////// Product names Get ///////////
		    String pn1a = "test 1";
		    String pn2a = "test 2";
		    String pn3a = "test 3";
		    String pn4a = "test 4";
		    ///////////////// Product names Get ///////////

		    ///////////////// Product price Get ///////////
		    int pp1a = 10;
		    int pp2a = 20;
		    int pp3a = 30;
		    int pp4a = 40;
		    int sum = pp1a + pp2a + pp3a + pp4a;
		    ///////////////// Product price Get ///////////

		    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
		    g2d.drawString("-------------------------------------", 12, y);
		    y += yShift;
		    g2d.drawString("      Restaurant Bill Receipt        ", 12, y);
		    y += yShift;
		    g2d.drawString("-------------------------------------", 12, y);
		    y += headerRectHeight;

		    g2d.drawString("-------------------------------------", 10, y);
		    y += yShift;
		    g2d.drawString(" Food Name                 T.Price   ", 10, y);
		    y += yShift;
		    g2d.drawString("-------------------------------------", 10, y);
		    y += headerRectHeight;
		    g2d.drawString(" " + pn1a + "                  " + pp1a + "  ", 10, y);
		    y += yShift;
		    g2d.drawString(" " + pn2a + "                  " + pp2a + "  ", 10, y);
		    y += yShift;
		    g2d.drawString(" " + pn3a + "                  " + pp3a + "  ", 10, y);
		    y += yShift;
		    g2d.drawString(" " + pn4a + "                  " + pp4a + "  ", 10, y);
		    y += yShift;
		    g2d.drawString("-------------------------------------", 10, y);
		    y += yShift;
		    g2d.drawString(" Total amount: " + sum + "               ", 10, y);
		    y += yShift;
		    g2d.drawString("-------------------------------------", 10, y);
		    y += yShift;
		    g2d.drawString("          Free Home Delivery         ", 10, y);
		    y += yShift;
		    g2d.drawString("             03111111111             ", 10, y);
		    y += yShift;
		    g2d.drawString("*************************************", 10, y);
		    y += yShift;
		    g2d.drawString("    THANKS TO VISIT OUR RESTUARANT   ", 10, y);
		    y += yShift;
		    g2d.drawString("*************************************", 10, y);
		    y += yShift;

//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
		} catch (Exception r) {
		    r.printStackTrace();
		}

		result = PAGE_EXISTS;
	    }
	    return result;
	}

    }

    protected static double convert_CM_To_PPI(double cm) {
	return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
	return inch * 72d;
    }

    public PageFormat getPageFormat(PrinterJob pj) {

	PageFormat pf = pj.defaultPage();
	Paper paper = pf.getPaper();

	double middleHeight = 8.0;
	double headerHeight = 2.0;
	double footerHeight = 2.0;
	double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
	double height = convert_CM_To_PPI(headerHeight + middleHeight + footerHeight);
	paper.setSize(width, height);
	paper.setImageableArea(
		0,
		10,
		width,
		height - convert_CM_To_PPI(1)
	);   //define boarder size    after that print area width is about 180 points

	pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
	pf.setPaper(paper);

	return pf;
    }

    //@SuppressWarnings("unchecked")
    private void doPrint() {
	PrinterJob pj = PrinterJob.getPrinterJob();
	pj.setPrintable(new BillPrintable(), getPageFormat(pj));
	try {
	    pj.print();

	} catch (PrinterException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Sri_xml_printer test = new Sri_xml_printer();
	test.doPrint();
    }

}
