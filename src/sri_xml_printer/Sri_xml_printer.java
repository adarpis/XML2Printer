/*
 * Copyright (C) 2019 aarpi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

import org.w3c.dom.Document;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import xml.XMLReader;

/**
 *
 * @author aarpi
 */
public class Sri_xml_printer {

    public class BillPrintable implements Printable {

        private Document xmldoc;

        public void setXMLDoc(String file) {
            XMLReader xreader = new XMLReader();
            this.xmldoc = xreader.docXMLBill(file);
        }

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int result = NO_SUCH_PAGE;

            if (pageIndex == 0 && xmldoc != null) {

                Element root = xmldoc.getDocumentElement();
                System.out.println("Root element :" + root.getNodeName() + " " + root.getAttribute("id") + " " + root.getAttribute("version"));
                if (root.getAttribute("id").equals("comprobante") && root.getAttribute("version").equals("1.0.0")) {
//                    System.out.println("----------------------------");
//
//                    NodeList nList = xmldoc.getElementsByTagName("infoTributaria");
//
//                    for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                        Node nNode = nList.item(temp);
//
//                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                            Element eElement = (Element) nNode;
//
//                            System.out.println("nombreComercial : " + eElement.getElementsByTagName("nombreComercial").item(0).getTextContent());
//                            System.out.println("razonSocial : " + eElement.getElementsByTagName("razonSocial").item(0).getTextContent());
//                            System.out.println("ruc : " + eElement.getElementsByTagName("ruc").item(0).getTextContent());
//                            System.out.println("claveAcceso : " + eElement.getElementsByTagName("claveAcceso").item(0).getTextContent());
//                            System.out.println("factura : " + eElement.getElementsByTagName("estab").item(0).getTextContent()
//                                    + "-" + eElement.getElementsByTagName("ptoEmi").item(0).getTextContent()
//                                    + "-" + eElement.getElementsByTagName("secuencial").item(0).getTextContent());
//                            System.out.println("dirMatriz : " + eElement.getElementsByTagName("dirMatriz").item(0).getTextContent());
//
//                        }
//                    }
//
//                    nList = xmldoc.getElementsByTagName("infoFactura");
//
//                    for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                        Node nNode = nList.item(temp);
//
//                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                            Element eElement = (Element) nNode;
//
//                            System.out.println("fechaEmision : " + eElement.getElementsByTagName("fechaEmision").item(0).getTextContent());
//                            System.out.println("dirEstablecimiento : " + eElement.getElementsByTagName("dirEstablecimiento").item(0).getTextContent());
//                            System.out.println("razonSocialComprador : " + eElement.getElementsByTagName("razonSocialComprador").item(0).getTextContent());
//                            System.out.println("identificacionComprador : " + eElement.getElementsByTagName("identificacionComprador").item(0).getTextContent());
//                            if (!eElement.getElementsByTagName("tipoIdentificacionComprador").item(0).getTextContent().equals("07")) {
//                                System.out.println("direccionComprador : " + eElement.getElementsByTagName("direccionComprador").item(0).getTextContent());
//                            }
//                            System.out.println("totalSinImpuestos : " + eElement.getElementsByTagName("totalSinImpuestos").item(0).getTextContent());
//                            System.out.println("importeTotal : " + eElement.getElementsByTagName("importeTotal").item(0).getTextContent());
//
//                        }
//                    }
//
//                    nList = xmldoc.getElementsByTagName("detalle");
//
//                    for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                        Node nNode = nList.item(temp);
//
//                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                            Element eElement = (Element) nNode;
//
//                            System.out.println("item : " + eElement.getElementsByTagName("cantidad").item(0).getTextContent()
//                                    + "\t" + eElement.getElementsByTagName("descripcion").item(0).getTextContent()
//                                    + "\t" + eElement.getElementsByTagName("precioUnitario").item(0).getTextContent()
//                                    + "\t" + eElement.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent());
//
//                        }
//                    }
//
//                    nList = xmldoc.getElementsByTagName("totalImpuesto");
//
//                    for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                        Node nNode = nList.item(temp);
//
//                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                            Element eElement = (Element) nNode;
//
//                            System.out.println("baseImponible : " + eElement.getElementsByTagName("baseImponible").item(0).getTextContent());
//                            System.out.println("tarifa : " + eElement.getElementsByTagName("tarifa").item(0).getTextContent());
//                            System.out.println("valor : " + eElement.getElementsByTagName("valor").item(0).getTextContent());
//
//                        }
//                    }
//
//                }

                    Graphics2D g2d = (Graphics2D) graphics;

                    //double width = pageFormat.getImageableWidth();
                    g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                    try {
                        /*Draw Header*/
                        int y = 20;
                        final int yShift = 10;
                        final int headerRectHeight = 15;

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

                        NodeList nList = xmldoc.getElementsByTagName("infoTributaria");

                        for (int temp = 0; temp < nList.getLength(); temp++) {

                            Node nNode = nList.item(temp);

                            System.out.println("\nCurrent Element :" + nNode.getNodeName());

                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                Element eElement = (Element) nNode;

                                g2d.drawString("-------------------------------------", 12, y);
                                y += yShift;
                                g2d.drawString("      " + eElement.getElementsByTagName("nombreComercial").item(0).getTextContent() + "        ", 12, y);
                                y += yShift;
                                g2d.drawString("      " + eElement.getElementsByTagName("razonSocial").item(0).getTextContent(), 12, y);
                                y += yShift;
                                g2d.drawString("RUC: " + eElement.getElementsByTagName("ruc").item(0).getTextContent(), 12, y);
                                y += yShift;
                                g2d.drawString(eElement.getElementsByTagName("claveAcceso").item(0).getTextContent(), 5, y);
                                y += yShift;
                                g2d.drawString("FACTURA # " + eElement.getElementsByTagName("estab").item(0).getTextContent()
                                        + "-" + eElement.getElementsByTagName("ptoEmi").item(0).getTextContent()
                                        + "-" + eElement.getElementsByTagName("secuencial").item(0).getTextContent(), 12, y);
                                y += yShift;
                                g2d.drawString("MATRIZ: " + eElement.getElementsByTagName("dirMatriz").item(0).getTextContent(), 12, y);
                                y += yShift;
                                g2d.drawString("-------------------------------------", 12, y);
                                y += headerRectHeight;

                                System.out.println("nombreComercial : " + eElement.getElementsByTagName("nombreComercial").item(0).getTextContent());
                                System.out.println("razonSocial : " + eElement.getElementsByTagName("razonSocial").item(0).getTextContent());
                                System.out.println("ruc : " + eElement.getElementsByTagName("ruc").item(0).getTextContent());
                                System.out.println("claveAcceso : " + eElement.getElementsByTagName("claveAcceso").item(0).getTextContent());
                                System.out.println("factura : " + eElement.getElementsByTagName("estab").item(0).getTextContent()
                                        + "-" + eElement.getElementsByTagName("ptoEmi").item(0).getTextContent()
                                        + "-" + eElement.getElementsByTagName("secuencial").item(0).getTextContent());
                                System.out.println("dirMatriz : " + eElement.getElementsByTagName("dirMatriz").item(0).getTextContent());

                            }
                        }

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
                        //y += yShift;

                    } catch (Exception r) {
                        r.printStackTrace();
                    }

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

    public void doPrint(String file) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        BillPrintable bPrint = new BillPrintable();
        bPrint.setXMLDoc(file);
        pj.setPrintable(bPrint, getPageFormat(pj));
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
        Sri_xml_printer PrintTest = new Sri_xml_printer();
        PrintTest.doPrint(args[0]);
    }

}
