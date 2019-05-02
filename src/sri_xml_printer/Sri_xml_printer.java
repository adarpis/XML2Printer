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

                    Graphics2D g2d = (Graphics2D) graphics;

                    final double width = pageFormat.getImageableWidth();
                    g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                    try {
                        /*Draw Header*/
                        int y = 20;
                        final int yShift = 10;
                        final int headerRectHeight = 15;

                        Font font = new Font("Monospaced", Font.PLAIN, 9);  // Support up to 38 characters
                        FontMetrics metrics = g2d.getFontMetrics(font);
                        g2d.setFont(font);

                        NodeList nList = xmldoc.getElementsByTagName("infoTributaria");

                        g2d.drawString("----------------------------------", 0, y);
                        y += yShift;

                        for (int temp = 0; temp < nList.getLength(); temp++) {

                            Node nNode = nList.item(temp);

                            System.out.println("\nCurrent Element :" + nNode.getNodeName());

                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                Element eElement = (Element) nNode;
                                String text = eElement.getElementsByTagName("nombreComercial").item(0).getTextContent();

                                g2d.drawString(text, (int) (width - metrics.stringWidth(text)) / 2, y);
                                y += yShift;
                                text = eElement.getElementsByTagName("razonSocial").item(0).getTextContent();
                                g2d.drawString(text, (int) (width - metrics.stringWidth(text)) / 2, y);
                                y += yShift;
                                text = "RUC: " + eElement.getElementsByTagName("ruc").item(0).getTextContent();
                                g2d.drawString(text, (int) (width - metrics.stringWidth(text)) / 2, y);
                                y += yShift;
                                text = eElement.getElementsByTagName("claveAcceso").item(0).getTextContent();
                                g2d.drawString("AUTH: " + text.substring(0, 24), 0, y);
                                y += yShift;
                                g2d.drawString(text.substring(24, 49), 30, y);
                                y += yShift;
                                if (eElement.getElementsByTagName("codDoc").item(0).getTextContent().equals("01")) {
                                    text = "FACTURA # ";
                                } else if (eElement.getElementsByTagName("codDoc").item(0).getTextContent().equals("07")) {
                                    text = "COMP. RET # ";
                                } else {
                                    text = "DOC. INVAL # ";
                                }
                                g2d.drawString(text + eElement.getElementsByTagName("estab").item(0).getTextContent()
                                        + "-" + eElement.getElementsByTagName("ptoEmi").item(0).getTextContent()
                                        + "-" + eElement.getElementsByTagName("secuencial").item(0).getTextContent(), 0, y);
                                y += yShift;
                                g2d.drawString("MATRIZ: " + eElement.getElementsByTagName("dirMatriz").item(0).getTextContent(), 0, y);
                                y += yShift;
                                //g2d.drawString("-------------------------------------", 0, y);
                                //y += headerRectHeight;

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

                        if (root.getNodeName().equals("factura")) {

                            nList = xmldoc.getElementsByTagName("infoFactura");

                            g2d.drawString("----------------------------------", 0, y);
                            y += yShift;

                            for (int temp = 0; temp < nList.getLength(); temp++) {

                                Node nNode = nList.item(temp);

                                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                    Element eElement = (Element) nNode;

                                    g2d.drawString("FECHA: " + eElement.getElementsByTagName("fechaEmision").item(0).getTextContent() + "        ", 0, y);
                                    y += yShift;
                                    g2d.drawString("D.EST: " + eElement.getElementsByTagName("dirEstablecimiento").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("CLIENTE: " + eElement.getElementsByTagName("razonSocialComprador").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("ID: " + eElement.getElementsByTagName("identificacionComprador").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    if (!eElement.getElementsByTagName("tipoIdentificacionComprador").item(0).getTextContent().equals("07")) {
                                        g2d.drawString("DIR: " + eElement.getElementsByTagName("direccionComprador").item(0).getTextContent(), 0, y);
                                        y += yShift;
                                    }
                                    g2d.drawString("SUBTOT: " + eElement.getElementsByTagName("totalSinImpuestos").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("TOTAL: " + eElement.getElementsByTagName("importeTotal").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("----------------------------------", 0, y);
                                    y += headerRectHeight;

                                    System.out.println("fechaEmision : " + eElement.getElementsByTagName("fechaEmision").item(0).getTextContent());
                                    System.out.println("dirEstablecimiento : " + eElement.getElementsByTagName("dirEstablecimiento").item(0).getTextContent());
                                    System.out.println("razonSocialComprador : " + eElement.getElementsByTagName("razonSocialComprador").item(0).getTextContent());
                                    System.out.println("identificacionComprador : " + eElement.getElementsByTagName("identificacionComprador").item(0).getTextContent());
                                    if (!eElement.getElementsByTagName("tipoIdentificacionComprador").item(0).getTextContent().equals("07")) {
                                        System.out.println("direccionComprador : " + eElement.getElementsByTagName("direccionComprador").item(0).getTextContent());
                                    }
                                    System.out.println("totalSinImpuestos : " + eElement.getElementsByTagName("totalSinImpuestos").item(0).getTextContent());
                                    System.out.println("importeTotal : " + eElement.getElementsByTagName("importeTotal").item(0).getTextContent());

                                }
                            }

                            nList = xmldoc.getElementsByTagName("detalle");

                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;
                            g2d.drawString(" CANT. DESCR.        PREC.  TOTAL ", 1, y);
                            y += yShift;
                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;

                            for (int temp = 0; temp < nList.getLength(); temp++) {

                                Node nNode = nList.item(temp);

                                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                    Element eElement = (Element) nNode;

                                    g2d.drawString(eElement.getElementsByTagName("cantidad").item(0).getTextContent(), 15, y);
                                    String text = eElement.getElementsByTagName("descripcion").item(0).getTextContent();
                                    if (text.length() > 12) {
                                        text = text.substring(0, 12);
                                    }
                                    g2d.drawString(text, 41, y);
                                    g2d.drawString(eElement.getElementsByTagName("precioUnitario").item(0).getTextContent(), 115, y);
                                    g2d.drawString(eElement.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent(), 152, y);
                                    y += yShift;

                                    System.out.println("item : " + eElement.getElementsByTagName("cantidad").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("descripcion").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("precioUnitario").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent());

                                }
                            }
                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;

                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;
                            g2d.drawString(" BASE.           IMP.       TOTAL ", 1, y);
                            y += yShift;
                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;

                            nList = xmldoc.getElementsByTagName("totalImpuesto");

                            for (int temp = 0; temp < nList.getLength(); temp++) {

                                Node nNode = nList.item(temp);

                                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                    Element eElement = (Element) nNode;

                                    g2d.drawString(eElement.getElementsByTagName("baseImponible").item(0).getTextContent(), 15, y);
                                    g2d.drawString(eElement.getElementsByTagName("tarifa").item(0).getTextContent(), 95, y);
                                    g2d.drawString(eElement.getElementsByTagName("valor").item(0).getTextContent(), 152, y);
                                    y += yShift;

                                    System.out.println("baseImponible : " + eElement.getElementsByTagName("baseImponible").item(0).getTextContent());
                                    System.out.println("tarifa : " + eElement.getElementsByTagName("tarifa").item(0).getTextContent());
                                    System.out.println("valor : " + eElement.getElementsByTagName("valor").item(0).getTextContent());

                                }
                            }

                            g2d.drawString("**********************************", 1, y);
                            y += yShift;
                            g2d.drawString("      GRACIAS POR SU VISITA       ", 1, y);
                            y += yShift;
                            g2d.drawString("Impreso gracias a: www.imaytec.com", 1, y);
                            y += yShift;
                            g2d.drawString("**********************************", 1, y);

                        }
                        
                        if (root.getNodeName().equals("comprobanteRetencion")) {

                            nList = xmldoc.getElementsByTagName("infoCompRetencion");

                            g2d.drawString("----------------------------------", 0, y);
                            y += yShift;

                            for (int temp = 0; temp < nList.getLength(); temp++) {

                                Node nNode = nList.item(temp);

                                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                    Element eElement = (Element) nNode;

                                    g2d.drawString("FECHA: " + eElement.getElementsByTagName("fechaEmision").item(0).getTextContent() + "        ", 0, y);
                                    y += yShift;
                                    g2d.drawString("D.EST: " + eElement.getElementsByTagName("dirEstablecimiento").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("R. SOCIAL: " + eElement.getElementsByTagName("razonSocialSujetoRetenido").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("ID: " + eElement.getElementsByTagName("identificacionSujetoRetenido").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("P. FISCAL: " + eElement.getElementsByTagName("periodoFiscal").item(0).getTextContent(), 0, y);
                                    y += yShift;
                                    g2d.drawString("----------------------------------", 0, y);
                                    y += headerRectHeight;

                                    System.out.println("fechaEmision : " + eElement.getElementsByTagName("fechaEmision").item(0).getTextContent());
                                    System.out.println("dirEstablecimiento : " + eElement.getElementsByTagName("dirEstablecimiento").item(0).getTextContent());
                                    System.out.println("razonSocialSujetoRetenido : " + eElement.getElementsByTagName("razonSocialSujetoRetenido").item(0).getTextContent());
                                    System.out.println("identificacionSujetoRetenido : " + eElement.getElementsByTagName("identificacionSujetoRetenido").item(0).getTextContent());
                                    System.out.println("periodoFiscal : " + eElement.getElementsByTagName("periodoFiscal").item(0).getTextContent());

                                }
                            }

                            nList = xmldoc.getElementsByTagName("impuesto");

                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;
                            g2d.drawString(" COD.  BASE      % RETEN.   VALOR ", 1, y);
                            y += yShift;
                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;

                            for (int temp = 0; temp < nList.getLength(); temp++) {

                                Node nNode = nList.item(temp);

                                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                    Element eElement = (Element) nNode;

                                    g2d.drawString(eElement.getElementsByTagName("codigoRetencion").item(0).getTextContent(), 15, y);
                                    g2d.drawString(eElement.getElementsByTagName("baseImponible").item(0).getTextContent(), 40, y);
                                    g2d.drawString(eElement.getElementsByTagName("porcentajeRetener").item(0).getTextContent(), 100, y);
                                    g2d.drawString(eElement.getElementsByTagName("valorRetenido").item(0).getTextContent(), 152, y);
                                    y += yShift;
                                    g2d.drawString(eElement.getElementsByTagName("numDocSustento").item(0).getTextContent(), 15, y);
                                    g2d.drawString(eElement.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent(), 115, y);
                                    y += yShift;
                                    
                                    System.out.println("item : " + eElement.getElementsByTagName("codigoRetencion").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("baseImponible").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("porcentajeRetener").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("valorRetenido").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("numDocSustento").item(0).getTextContent()
                                            + "  " + eElement.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent()
                                    );

                                }
                            }
                            g2d.drawString("----------------------------------", 1, y);
                            y += yShift;

                            g2d.drawString("**********************************", 1, y);
                            y += yShift;
                            g2d.drawString("Impreso gracias a: www.imaytec.com", 1, y);
                            y += yShift;
                            g2d.drawString("**********************************", 1, y);

                        }

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

        double middleHeight = 90.0;
        double headerHeight = 4.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(9.4);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight + middleHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                4,
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
