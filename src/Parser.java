import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *

 Given “tag” data comes in the following format:

TAG  LENGTH  DATA  TAG  LENGTH  DATA (repeats)

The “TAG” is 4 bytes long and identifies the “type” of tag it is.
LENGTH is 2 bytes long and indicates the length of the DATA
DATA is the data associated with the tag

Below class is a parser for this data that takes input as a hex string, parses
the data into a collection and then prints it out.

It doesn't work.  Please debug.
*/


class Parser {
    public static final String INPUT_1 = "00009F090002010500009F1B00040000000000009F330003E028C800009F3500012200009F660004320040000000DF020001080000DFE2000405F5E0FF0000DFE30004000000000000DFE40004000009C40000DFE500010100DF810C000103CFFF8001000100CFFF8002000100CFFFFF00003050849F359F029F039F099F26829F365F349F7C9F6E9F109F1A95575F2A9A9C9F375F349F669F39DF029F339F275F245A";
    public static final String INPUT_2 = "0000CC9500010100005F2A0002084000005F3600010200009F060008A000000152401000009F090002000100009F1B00040000000000009F3500012200009F660004B600400000DF812D00030000130000DFDB0001000000DFDD0001000000DFDE0001000000DFDF0001010000DFE10001010000DFE500010100009F330003E0F8C8DFFFDF00000100DFFFDF020006000999999999DFFFDF030006000000000000DFFFDF04000600000000500000DF810C000106CFFFFF0200010100DF81200005DC0000200000DF81210005001000000000DF81220005FCE09CF800";

    public static String readType(String str, int position, int tagLen) {
        System.out.println("readType:"+str.substring(position, position + tagLen));
        return str.substring(position, position + tagLen);
    }

    public static int readLength(String hexString, int position, int lengthLen) {
        System.out.println("readLength:"+hexString.substring(position, position + lengthLen));
        String hexLength = hexString.substring(position, position + lengthLen);
        return Integer.parseInt(hexLength, 16);
    }

    public static String readValue(String hexString, int position, int length) {
        return hexString.substring(position, position + length);

    }

    public static void printList(List<TagContainer> toPrint) {
        for (TagContainer c : toPrint) {
            c.print(System.out);
        }
    }

    public static class TagContainer implements Comparable<TagContainer> {
        String tagHex;
        String tagValue;
        String valueLength;

        public void print(PrintStream printStream) {
            System.out.println("Hex type: " + tagHex);
            System.out.println("  Length: " + valueLength);
            System.out.println("  Hex Value: " + tagValue);

            printStream.println();
        }


        @Override
        public int compareTo(TagContainer o) {
            long thisTagValue = Long.parseLong(tagHex, 16);
            long thatTagValue = Long.parseLong(o.tagHex, 16);
            return thisTagValue == thatTagValue ? 0 : (thisTagValue
                    - thatTagValue < 0 ? -1 : 1);
        }

    }

    public List<TagContainer> parse(String hexString, int tagLen)
            throws Exception {
        List<TagContainer> containerList = new ArrayList<TagContainer>();
        int position = 0;
        while(position < hexString.length()){
            // System.out.println("enter pos" + position);
            String hexType = readType(hexString, position, tagLen*2);
            position +=tagLen*2;
            // System.out.println("plus tage pos" + position+" substring:"+hexString.substring(0,position));
            int lengthLen = 2;
            int length = readLength(hexString, position, lengthLen*2);
            System.out.println("after len tage pos" + position + " len:"+length);
            position += lengthLen*2;
            String value = readValue(hexString, position, length*2);
            position += 2*length;

            TagContainer container = new TagContainer();
            container.tagValue = value;
            container.valueLength = length + "";
            container.tagHex = hexType;
            containerList.add(container);
        }

        Collections.sort(containerList);

        return containerList;
    }

    public static void main(String[] args) throws Exception {
        Parser parser = new Parser();
        // System.out.println(INPUT_1.length());
        List<TagContainer> input1 = parser.parse(INPUT_1, 4);
        // List<TagContainer> input3 = parser.parse("00009F0900020105", 4);
        // printList(input3);
        printList(input1);
        // List<TagContainer> input2 = parser.parse(INPUT_2, 4);
        // printList(input2);

    }
}
