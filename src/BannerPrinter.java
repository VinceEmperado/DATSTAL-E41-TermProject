// Just the banners. One place to change it, and every menu in the program looks the same
public class BannerPrinter {

    static void printBanner(String bannerTitle) {

        int bannerWidth = 40;
        int leftSpace = (bannerWidth - bannerTitle.length()) / 2;

        IO.println("");
        IO.println("=".repeat(bannerWidth));
        IO.println(" ".repeat(Math.max(leftSpace, 0)) + bannerTitle);
        IO.println("=".repeat(bannerWidth));
    }
}