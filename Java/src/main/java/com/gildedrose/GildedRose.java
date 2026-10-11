package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        switch (item.name) {
            case SULFURAS:
                new LegendaryItemUpdater().update(item);
                return;
            case AGED_BRIE:
                new AgedBrieItemUpdater().update(item);
                return;
            case BACKSTAGE_PASSES:
                new BackstageItemUpdater().update(item);
                return;
            case CONJURED:
                new ConjuredItemUpdater().update(item);
                return;
            default:
                new NormalItemUpdater().update(item);
        }
    }
}
