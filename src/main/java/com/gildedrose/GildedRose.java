package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            ItemUpdater itemUpdater = getUpdater(item);
            itemUpdater.update();
        }
    }

    private ItemUpdater getUpdater(Item item) {
        String name = item.name.toLowerCase();
        if (name.contains("aged brie")) {
            return new AgedBrieUpdater(item);
        } else if (name.contains("sulfuras")) {
            return new SulfurasUpdater(item);
        } else if (name.contains("backstage passes")) {
            return new BackstagePassUpdater(item);
        } else if (name.contains("conjured")) {
            return new ConjuredUpdater(item);
        } else {
            return new NormalItemUpdater(item);
        }
    }
}
