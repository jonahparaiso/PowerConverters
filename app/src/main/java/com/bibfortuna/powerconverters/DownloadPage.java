import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class DownloadPage {

    public static void main(String[] args) throws IOException {

        // Make a URL to the web page
        String forceFeatsAddress = "http://force.wikidot.com/force-feats";
        String regularFeatsAddress = "http://force.wikidot.com/feats";
        HashMap<String,String> forceFeatMap = scrapeFeats(forceFeatsAddress);
        HashMap<String,String> regularFeatMap = scrapeFeats(regularFeatsAddress);

        //printHashmap(forceFeatMap);
        printHashmap(regularFeatMap);
    }

    private static String formatFeatLine(String featLine, boolean printFormatting) {
        if (printFormatting)
            System.out.printf("Formatting      :{%s}%n", featLine);
        boolean capitalize = false;
        if (featLine.contains("strong>")) {
            capitalize = true;
        }
        if (featLine.contains("href")) {
            int fromName, toName;
            if (featLine.contains(",")) {
                if (featLine.contains("</")) {
                    fromName = featLine.indexOf("\">");
                    toName = featLine.indexOf("</");
                    featLine = featLine.substring(fromName + 2, toName);
                    featLine = featLine.concat(",");
                } else {
                    fromName = featLine.indexOf("\">");
                    toName = featLine.length();
                    featLine = featLine.substring(fromName + 2, toName);
                }
            } else if (featLine.contains(".")) {
                if (featLine.contains("</")) {
                    fromName = featLine.indexOf("\">");
                    toName = featLine.indexOf("</");
                    featLine = featLine.substring(fromName + 2, toName);
                    featLine = featLine.concat(".");
                } else {
                    fromName = featLine.indexOf("\">");
                    toName = featLine.length();
                    featLine = featLine.substring(fromName + 2, toName);
                }
            } else {
                if (featLine.contains("</")) {
                    fromName = featLine.indexOf("\">");
                    toName = featLine.indexOf("</");
                    featLine = featLine.substring(fromName + 2, toName);
                } else {
                    fromName = featLine.indexOf("\">");
                    toName = featLine.length();
                    featLine = featLine.substring(fromName + 2, toName);
                }
            }
        } else {
            featLine = featLine.replace("<p>", "\n").replace("</p>", "");
            featLine = featLine.replace("<td>", "").replace("</td>", "");
            featLine = featLine.replace("<tr>", "").replace("</tr>", "");
            featLine = featLine.replace("<sup>", "").replace("</sup>", "");
            featLine = featLine.replace("<strong>", "").replace("</strong>", "");
            featLine = featLine.replace("/>", "").replace("<br", "\n");
            featLine = featLine.replace("<em>", "\n").replace("</em>", "");
            featLine = featLine.replace("<a", "").replace(" .", ".").replace("</a>", "");
            featLine = featLine.replace("-attribute\">", "");
            featLine = featLine.replace("Benefit:", "\nBenefit:").replace("Benefits:", "\nBenefits:");
            featLine = featLine.replace("Normal:", "\nNormal:").replace("Special:", "\nSpecial:");
            if (capitalize) {
                featLine = featLine.toUpperCase();
            }
        }
        if (printFormatting)
            System.out.printf("Done formatting :{%s}%n", featLine);
        return featLine;
    }

    private static boolean isNumeric(String strNum) {
        //System.out.printf("%n%nTesting isNumeric for %s%n", strNum);
        double d;
        try {
            if (strNum.contains(",")) {
                strNum = strNum.replace(",", "");
                d = Double.parseDouble(strNum);
                strNum.concat(",");
            } else
                d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            //System.out.printf("%s isNumeric = %b%n", strNum, false);
            return false;
        }
        //System.out.printf("%s isNumeric = %b%n", strNum, true);
        return true;
    }

    private static String removeName(String str) {
        int from = str.indexOf("\n", str.indexOf("\n") + 1);
        //System.out.printf("Found instance of newLine at index %d%n", from);
        return str.substring(from, str.length());
    }

    private static String findBaseAddress(String str) {
        int to = str.indexOf(".com/");
        return str.substring(0, to + 4);
    }

    private static HashMap<String,String> scrapeFeats(String address) {

        String baseAddress = findBaseAddress(address);

        // Get the input stream through URL Connection
        try {
            URL pageLocation = new URL(address);
            Scanner in = new Scanner(pageLocation.openStream());
            Scanner featIn;
            int from, to, fromName, toName, charcount;
            String line, capsName;
            String buff = "";
            boolean buffFlip = true;
            String featAddress, featLine, featInfo;
            StringBuilder featBody;
            HashMap<String, String> featMap = new HashMap<>();
            //loop through the menu page
            while (in.hasNext()) {
                line = in.next();
                //Find the individual Feat links to their respective URLs
                if (line.contains("href") && line.contains("-feat") && !line.matches("href=\"/force-feats\">Force")) {
                    //Grabbing the link to the Feat page
                    from = line.indexOf("\"");
                    to = line.lastIndexOf("\"");
                    //Parsing the name of the Feat
                    fromName = line.indexOf("/");
                    toName = line.indexOf("-feat");
                    capsName = line.substring(fromName + 1, toName).toUpperCase();
                    //System.out.printf("CapsName = %s%n", capsName);
                    featAddress = baseAddress + line.substring(from + 1, to);
                    featIn = new Scanner(new URL(featAddress).openStream());
                    //System.out.printf("Scanning %s's URL.%n%n", capsName);
                    /**Scanning the individual Feat's URL*/
                    while (featIn.hasNext()) {
                        charcount = 0;
                        featBody = new StringBuilder();
                        featLine = featIn.next();
                        //System.out.println(featLine);
                        /**Making sure we are at the page content, where the data we want exists*/
                        if (featLine.contains("page-content")) {

                            //System.out.println("Reached page content");
                            /**Looping throughout the page content, which ends with "</div>"*/
                            while (featIn.hasNext()) {
                                featLine = featIn.next();
                                if (featLine.contains("</div>"))
                                    break;
                                //Checking to see if it's the end of the file
                                featLine = formatFeatLine(featLine, false);
                                if (featLine.length() != 0) {
                                    featBody.append(featLine + " ");
                                    charcount += featLine.length();
                                    if (charcount > 100) {
                                        featBody.append("\n");
                                        charcount = 0;
                                    }
                                    else if (featBody.charAt(featBody.length()-1) == '\n')
                                        charcount = 0;
                                }
                            }
                        }
                        //featBody.append(featLine+"\n");
                        if (featBody.length() != 0) {
                            featInfo = featBody.toString().replace(" ,", ",").replace(" .", ".\n");
                            featInfo = removeName(featInfo).concat("\n");
                            featMap.put(capsName, featInfo);

                            //System.out.printf("Before%nbuff = {%s}%nbuffFlip = %b%nbuffLength = %d%n", buff, buffFlip, buff.length());
                            if (buff.length() == 25 || buff.length() == 0)
                                buffFlip = !buffFlip;
                            if (buffFlip)
                                buff = buff.substring(0, buff.length()-1);
                            else
                                buff = buff.concat(".");
                            //buff = buffFlip ? buff.substring(1,buff.length()) : buff.concat(" ");
                            //System.out.printf("After%nbuff = {%s}%nbuffFlip = %b%nbuffLength = %d%n", buff, buffFlip, buff.length());
                            System.out.printf("%s.%n", buff);
                        }
                    }
                }
            }
            return featMap;
        }
        catch (MalformedURLException e) {
            System.out.println("Entered an incorrect web address, make sure spelling is perfect");
            return null;
        }
        catch (java.io.IOException e) {
            System.out.println("Java scanner failed, make sure spelling is correct on the web address in scrapeFeats(String address)");
            return null;
        }
    }

    private static void printHashmap(HashMap<String,String> map) {
        System.out.println("\nPrinting HashMap\n");
        for (String key : map.keySet()) {
            System.out.println(key + map.get(key));
        }
        System.out.println("\nFinished printing HashMap\n");
    }

}