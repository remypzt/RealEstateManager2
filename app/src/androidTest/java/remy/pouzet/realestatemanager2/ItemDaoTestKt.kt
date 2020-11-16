package remy.pouzet.realestatemanager2

/**
 * Created by Remy Pouzet on 16/11/2020.
 */

//
//@RunWith(AndroidJUnit4::class)
//class ItemDaoTestKt {
//    // this is RoomDatabase
//    private lateinit var db: EstateDatabase
//
//    @Before
//    fun init() {
//        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
//                EstateDatabase::class.java).build()
//    }
//
//    @After
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    fun testInsertValue() {
//
//        var item = Estate ("Type", "City", 0, "R.drawable.ic_add", 1, "Description", 0, 0, "Adress", "Status", "Agent");
////        assertThat(item.type, Is("Type"))
//
//        // could create Kotlin extension to simplified this code
//        db.beginTransaction()
//        db.mEstateDao().createEstate(item)
//        db.setTransactionSuccessful()
//        db.endTransaction()
//
//        var items = getValue(db.mEstateDao().fetchAll())
//        assertThat(items.size, Is(1))
//
//        item = items[0]
//        assertThat(item.type, Is("Type"))
//    }
//}