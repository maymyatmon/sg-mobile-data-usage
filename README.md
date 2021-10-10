# sg-mobile-data-usage

I'm still learning and new to Kotlin since all of my pass android projects are with java. 

#DateSource
https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=30

- Created ApiInterface with retrofit to comunicate with API.
- Created DataResponse, Data and YearlyRecord models to map API response.
- MainViewModel to call API and get API response.


#Task ONE: Filter and Display total consumption by year

First page >> showing yearly total consumption from 2005 to 2010 in Recycler View
- Read data from MainViewModel and filter them to show yearly. 
- Show filtered results in Recycler View with ItemAdapter.


#Task TWO: Display total consumption for selected year only

Second page >> showing data usage with quarterly breakdown from 2005 to 2010 in View Pager
- Read data from MainViewModel and filter them to show quarterly. 
- Show filtered results in View Pager with PagerAdapter.
- Generated debug log "Active Page" to track currently visiable page.

View Screen Recording here
https://drive.google.com/file/d/1Yjn8ZrC48QZUTn0b-G1bj8OyYAyOaLX4/view?usp=sharing
