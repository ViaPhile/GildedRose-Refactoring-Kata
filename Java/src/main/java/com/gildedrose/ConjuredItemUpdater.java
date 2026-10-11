package com.gildedrose;

/**
 * GildedRose-Refactoring-Kata
 *
 * @author vianney On 11/10/2026
 */
public class ConjuredItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn--;
        QualityHelper.decrease(item, item.sellIn < 0 ? 4 : 2);
    }
}
