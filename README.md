## Challenges Faced

The main challenge encountered during the development of this project was that "The Metropolitan Museum of Art" API does not provide any parameter to limit the number of results. This limitation has been discussed in detail in the following links:
- https://github.com/metmuseum/openaccess/issues/40
- https://metmuseum.github.io/

To overcome this challenge, I decided to implement a pagination logic in the app. The following steps were taken:
1. I called the API to search for the query.
2. All results were retrieved and saved in the local database, even if there were more than 100 results.
3. Only the first 20 results were passed to the UI.
4. When the user scrolls to the end of the page, the next 20 results are fetched from the local database.
5. If the user searches for the same query again, the app checks the local database for any data beyond the first 20 results, and if there is none, it makes a new API call to fetch more data.
