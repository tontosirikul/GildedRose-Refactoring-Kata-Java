package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;

class GildedRoseTest {
    @Nested
    class DefaultItemBehaviour {
        @Test
        void shouldHaveNameValue() {
            Item[] items = new Item[] { new Item("foo", 0, 0) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals("foo", app.items[0].name);
        }
    
        @Test
        void shouldDecreaseSellInValue() {
            Item[] items = new Item[] { new Item("foo", 10, 20) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(9, app.items[0].sellIn);
        }
    
        @Test
        void shouldDecreaseQualityValue() {
            Item[] items = new Item[] { new Item("foo", 10, 20) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(19, app.items[0].quality);
        }
    
        @Test
        void shouldDecreaseQualityTwiceAfterSellDate() {
            Item[] items = new Item[] { new Item("foo", 0, 20) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(-1, app.items[0].sellIn);
            assertEquals(18, app.items[0].quality);
        }
    
        @Test
        void shouldNotAllowNegativeQuality() {
            Item[] items = new Item[] { new Item("foo", 10, 0) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(0, app.items[0].quality);
        }
    }

    @Nested
    class SpecialtyItemBehaviour {
        @Nested
        class AgedBrie {
            @Test
            void shouldIncreaseQualityValue() {
                Item[] items = new Item[] { new Item("Aged Brie", 10, 20) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(9, app.items[0].sellIn);
                assertEquals(21, app.items[0].quality);
            }

            @Test
            void shouldHaveQualityLimitAtFifty() {
                Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(9, app.items[0].sellIn);
                assertEquals(50, app.items[0].quality);
            }
        }

        @Nested
        class Sulfuras {
            @Test
            void shouldChangeQualityAndSellIn() {
                Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 20) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(10, app.items[0].sellIn);
                assertEquals(20, app.items[0].quality);
            }
        }

        @Nested
        class BackstagePasses {
            @Test
            void shouldIncreaseQualityEvelenDaysOrMore() {
                Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(10, app.items[0].sellIn);
                assertEquals(21, app.items[0].quality);
            }

            @Test
            void shouldHaveQualityLimitAtFiftyForEvelenDaysOrMore() {
                Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(10, app.items[0].sellIn);
                assertEquals(50, app.items[0].quality);
            }

            @Test
            void shouldIncreaseQualityTwiceTenDays() {
                Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(9, app.items[0].sellIn);
                assertEquals(22, app.items[0].quality);
            }

            @Test
            void shouldHaveQualityLimitAtFiftyForTenDays() {
                Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(9, app.items[0].sellIn);
                assertEquals(50, app.items[0].quality);
            }

            @Test
            void shouldIncreaseQualityTripleFiveDays() {
                Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(4, app.items[0].sellIn);
                assertEquals(23, app.items[0].quality);
            }

            @Test
            void shouldHaveQualityLimitAtFiftyForFiveDays() {
                Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(4, app.items[0].sellIn);
                assertEquals(50, app.items[0].quality);
            }

            @Test
            void shouldExpired() {
                Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(-1, app.items[0].sellIn);
                assertEquals(0, app.items[0].quality);
            }
        }

        @Nested
        class ConjuredItem {
            @Test
            void shouldDecreasedQualityTwice() {
                Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 20) };
                GildedRose app = new GildedRose(items);
                app.updateQuality();
                assertEquals(9, app.items[0].sellIn);
                assertEquals(18, app.items[0].quality);
            }

            @Test
        void shouldDecreaseQualityTwiceAfterSellDate() {
            Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 20) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(-1, app.items[0].sellIn);
            assertEquals(16, app.items[0].quality);
        }
        }
    }
}
