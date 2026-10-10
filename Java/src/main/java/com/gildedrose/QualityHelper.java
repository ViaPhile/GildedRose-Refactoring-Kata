package com.gildedrose;

/**
 * GildedRose-Refactoring-Kata
 *
 * @author vianney On 10/10/2026
 */
public final class QualityHelper {
    static final int MIN = 0;
    static final int MAX = 50;

    private QualityHelper() {
        // classe utilitaire
    }

    public static void increase (Item item, int amount) {
        if (item.quality < MAX) {
            item.quality = Math.min(MAX, item.quality + amount);
        }
    }

    public static void decrease (Item item, int amount) {
        if (item.quality > MIN) {
            item.quality = Math.max(MIN, item.quality - amount);
        }
    }
}
