package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

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
        if (item.name.equals(SULFURAS)) {
            new LegendaryItemUpdater().update(item);
            return;
        }
        if (AGED_BRIE.equals(item.name)) {
            new AgedBrieUpdater().update(item);
            return;
        }
        if (BACKSTAGE_PASSES.equals(item.name)) {
            new BackstagePassUpdater().update(item);
            return;
        }

        new NormalItemUpdater().update(item);
    }
}
