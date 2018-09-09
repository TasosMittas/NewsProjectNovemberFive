# NewsProjectNovemberFive

The app was built as an assignment from November Five. The purpose of the app is to show the news as a list of article titles, taken from the nieuws.vtm.be feed on which the user can tap and read the full article.

The app consists of two activities.

The first one is the MainActivity in which the list of news is presented. The activity makes use of a RecyclerView which helps in presenting a smooth list and retains itself on screen rotation. On each item there is a thumbnail, the title and the date/time the article was uploaded.

The second acticity is the DetailActivity in which the full article is presented along with a larger-sized picture.

The app uses the Model–view–viewmodel design pattern. Room Persistence Library is used for the purpose of saving the articles locally, for offline read. The data is fetched using Retrofit. In order for the neuws.vtm.be API to communicate with the local database, a Repository class was made which fetces the data and saves it locally. Moreover, for the view to communicate with the underlying data, a viewmodel is used to pass data to the adapter responsible for handling the MainActivity views. LiveData is used so that an Observer will be notified about modifications of the data .

Lastly, the Glide library was used for image loading.
