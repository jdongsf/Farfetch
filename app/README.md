# Farfetch
Fetch json in MainViewModel.kt, it wil run in non-UI thread.
Then  filter null/empty name, then sorted by listId/name, then group listId, post liveData updates.
It will reflect it on the UI. Because we have observed the liveData status in MainActivity.
