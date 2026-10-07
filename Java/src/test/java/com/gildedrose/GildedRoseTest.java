package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GildedRoseTest {
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String ELIXIR = "Elixir of the Mongoose";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static Item updateItemOnce(String name, int sellIn, int quality) {
        Item item = new Item(name, sellIn, quality);
        new GildedRose(new Item[]{item}).updateQuality();
        return item;
    }

    @Test
    void normalItemDegradesByOnce_beforeSellDate() {
        Item item = updateItemOnce(ELIXIR, 5, 7);

        assertEquals(4, item.sellIn);
        assertEquals(6, item.quality);
    }

    @Test
    void normalItemDegradesByTwo_afterSellDate() {
        Item item = updateItemOnce(ELIXIR, 0, 10);

        assertEquals(8, item.quality);
    }

    @Test
    void qualityIsNeverNegative_evenAfterSellDate() {
        // 1ère contrainte : la qualité n'est jamais négative
        Item item = updateItemOnce(ELIXIR, 0, 1);

        assertEquals(0, item.quality);
    }

    @Test
    void agedBrieIncreasesInQuality() {
        Item item = updateItemOnce(AGED_BRIE, 5, 10);

        assertEquals(11, item.quality);
    }

    @Test
    void agedBrieIncreasesByTwo_afterSellDate() {
        Item item = updateItemOnce(AGED_BRIE, 0, 10);

        assertEquals(12, item.quality);
    }

    @Test
    void qualityNeverExceedsFifty() {
        // sellIn étant à 0, on s'attendrait logiquement à ce que le quality prenne +2 (donc 51)
        // sauf qu'elle est bloqué par la 2ème contrainte : quality <= 50
        Item item = updateItemOnce(AGED_BRIE, 0, 49);

        assertNotEquals(51, item.quality);
        assertEquals(50, item.quality);
    }

    @Test
    void sulfurasNeverChanges() {
        Item item = updateItemOnce(SULFURAS, 0, 80);

        assertEquals(0, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void backstageIncreasesByOnce_whenMoreThanTenDays() {
        //si la date du concert est X > 10j alors quality + 1
        Item item = updateItemOnce(BACKSTAGE, 11, 20);

        assertEquals(21, item.quality);
    }

    @Test
    void backstageIncreasesByTwo_whenBetweenFiveAndTenDays() {
        //si la date du concert est entre 5j et 10j cad 5j < X <= 10j alors quality + 2
        Item item1 = new Item(BACKSTAGE, 10, 20);
        Item item2 = new Item(BACKSTAGE, 8, 21);
        Item item3 = new Item(BACKSTAGE, 5, 22);

        Item[] items =  new Item[]{item1, item2, item3};
        new GildedRose(items).updateQuality();

        assertEquals(22, item1.quality);
        assertEquals(23, item2.quality);
        assertNotEquals(24, item3.quality);
    }

    @Test
    void backstageIncreasesByTree_whenFiveDaysOrLess() {
        // si la date du concert est <= 5j alors quality + 3
        Item item = updateItemOnce(BACKSTAGE, 5, 20);

        assertEquals(23, item.quality);
    }

    @Test
    void backstageNeverExceedsFifty() {
        // étant donné que la date du concert <= 5j, on s'attendrait ici que quality passe à 52
        // mais la 2eme contrainte impose que la qualité ne depasse jamais 50
        Item item = updateItemOnce(BACKSTAGE, 5, 49);

        assertNotEquals(52, item.quality);
        assertEquals(50, item.quality);
    }

    @Test
    void backstageDropsQualityToZero_afterConcert() {
        Item item = updateItemOnce(BACKSTAGE, 0, 20);

        assertEquals(0, item.quality);
    }

}
