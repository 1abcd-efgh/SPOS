import java.util.*;

class MacroProcessor {

    static class MNTEntry {
        String name;
        int pp, kp, mdtp, kpdtp;

        public MNTEntry(String name, int pp, int kp, int mdtp, int kpdtp) {
            this.name = name;
            this.pp = pp;
            this.kp = kp;
            this.mdtp = mdtp;
            this.kpdtp = kpdtp;
        }
    }

    static class MDTEntry {
        int index;
        String definition;
    
        public MDTEntry(int index, String definition) {
            this.index = index;
            this.definition = definition;
        }
    }

    static List<MNTEntry> mnt = new ArrayList<>();
    static List<MDTEntry> mdt = new ArrayList<>();
    static Map<String, String> kpdt = new HashMap<>();
    static List<String> intermediateCode = new ArrayList<>();

    public static void main(String[] args) {

        List<String> sourceCode = Arrays.asList(
            "MACRO",
            "INCR &ARG1, &ARG2=1",
            "L &ARG1, &ARG1, &ARG2",
            "MEND",
            "START",
            "INCR A,5",
            "INCR B",
            "END"
        );

        passOne(sourceCode);
        passTwo();
    }

    public static void passOne(List<String> sourceCode) {
        boolean inMacroDef = false;
        String macroName = "";
        int mdtIndex = mdt.size();
        int kpIndex = 0;

        for (String line : sourceCode) {
            if (line.startsWith("MACRO")) {
                inMacroDef = true;
            } else if (line.startsWith("MEND")) {
                inMacroDef = false;
            } else if (inMacroDef) {
                if (macroName.isEmpty()) {

                    String[] parts = line.split(" ");
                    macroName = parts[0];
                    int pp = 0, kp = 0;
                    for (String part : parts) {
                        if (part.contains("=")) {
                            kpdt.put(part.split("=")[0], part.split("=")[1]);
                            kp++;
                        } else {
                            pp++;
                        }
                    }
                    mnt.add(new MNTEntry(macroName, pp, kp, mdt.size(), kpIndex));
                    kpIndex += kp;
                } else {

                    mdt.add(new MDTEntry(mdtIndex++, line));
                }
            }else{

                intermediateCode.add(line);
            }
        }
    }

        public static void passTwo() {
            List<String> output = new ArrayList<>();
            for (String line : intermediateCode) {
                if (line.startsWith("INCR")) {
                    String[] parts = line.split(" ");
                    String macroName = parts[0];
                    String[] args = parts[1].split(",");

                    MNTEntry mntEntry = mnt.stream()
                        .filter(e -> e.name.equals(macroName))
                        .findFirst().orElse(null);
                    if (mntEntry !=null) {
                        for (int i = mntEntry.mdtp; i < mdt.size() && !mdt.get(i).definition.equals("MEND"); i++){
                            String defLine = mdt.get(i).definition;
                            defLine= defLine.replace("&ARG1", args[0].trim());
                            defLine= defLine.replace("&ARG2", args.length > 1 ? args[1].trim() : kpdt.get("&ARG2"));
                            output.add(defLine);
                    }
                }
            } else {
                output.add(line);
            }
        }
        output.forEach(System.out::println);
    }
}
