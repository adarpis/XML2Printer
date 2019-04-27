/*
 * Copyright (C) 2019 aarpi <aarpi@t.org>
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
package xml;

/**
 *
 * @author aarpi <aarpi@t.org>
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLReader {

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("C:/Users/adria/Documents/aarpi/temp/dirs/gen/2704201901010428015100110010010000000041234567817.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            System.out.println("Root element :" + root.getNodeName() + " " + root.getAttribute("id") + " " + root.getAttribute("version"));

            if (root.getAttribute("id").equals("comprobante") && root.getAttribute("version").equals("1.0.0")) {
                System.out.println("----------------------------");

                NodeList nList = doc.getElementsByTagName("infoTributaria");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

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

                nList = doc.getElementsByTagName("infoFactura");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

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

                nList = doc.getElementsByTagName("detalle");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        System.out.println("item : " + eElement.getElementsByTagName("cantidad").item(0).getTextContent()
                                + "\t" + eElement.getElementsByTagName("descripcion").item(0).getTextContent()
                                + "\t" + eElement.getElementsByTagName("precioUnitario").item(0).getTextContent()
                                + "\t" + eElement.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent());

                    }
                }
                
                nList = doc.getElementsByTagName("totalImpuesto");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        System.out.println("baseImponible : " + eElement.getElementsByTagName("baseImponible").item(0).getTextContent());
                        System.out.println("tarifa : " + eElement.getElementsByTagName("tarifa").item(0).getTextContent());
                        System.out.println("valor : " + eElement.getElementsByTagName("valor").item(0).getTextContent());

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
