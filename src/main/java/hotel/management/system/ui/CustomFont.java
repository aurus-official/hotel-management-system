package hotel.management.system.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomFont {
    public static void setup() {
        CustomFont.setupFont();
    }

    private static void setupFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    new File("./src/main/java/hotel/management/system/assets/InriaSerif-Regular.ttf")));
            List<String> fonts = new ArrayList<>(Arrays.asList(ge.getAvailableFontFamilyNames()));
            System.out.println(fonts.contains("Inria Serif"));
            // System.out.println(Stream.of(ge.getAvailableFontFamilyNames()).reduce("",
            // (result, string) -> result.concat(string + ",\n")));

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
