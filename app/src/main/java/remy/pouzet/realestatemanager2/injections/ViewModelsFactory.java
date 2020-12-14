package remy.pouzet.realestatemanager2.injections;

/**
 * Created by Remy Pouzet on 17/11/2020.
 * //
 */

//TODO erase
//public class ViewModelsFactory implements ViewModelProvider.Factory {

//    private final EstateRepository estateDataSource;
//    private final Executor executor;
//
//    public ViewModelsFactory(EstateRepository estateDataSource, Executor executor) {
//        this.estateDataSource = estateDataSource;
//        this.executor = executor;
//    }
//
//    @Override
//    public <T extends ViewModel> T create(Class<T> modelClass) {
////		if (modelClass.isAssignableFrom(EstatesListViewModel.class)) {
////			return (T) new EstatesListViewModel(estateDataSource, executor);
////		}
//
//        if (modelClass.isAssignableFrom(FormViewModel.class)) {
//            return (T) new FormViewModel(estateDataSource, executor);
//        }
//        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
//            return (T) new SearchViewModel(estateDataSource, executor);
//        }
////        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
////            return (T) new DetailsViewModel(estateDataSource, executor);
////        }
//        throw new IllegalArgumentException("Unknown ViewModel class");
//    }
//}