# Report Metrics Solution

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.org/mslinn/hacker-rank.svg?branch=master)](https://travis-ci.org/mslinn/report-metrics)
[![GitHub version](https://badge.fury.io/gh/mslinn%2Fhacker-rank.svg)](https://badge.fury.io/gh/mslinn%2Freport-metrics)

## Background: 

We would like to start building a general reporting framework to compute basic metrics, such as median or total records processed from an IO source. 

To make the problem more concrete, We are given a CSV file with a list of user data, specifically, the user id, age and favorite color. For these records, we would like to compute several metrics (defined below) and return a summary. In general, we don't know the file size, or the number of streamed records that will be provided (and it might not fit into memory).

It's suggested to use either python or scala, however, you should use the language you're most proficient in. You should restrict your solution to use on the standard library of the language chosen.

## Requirements

- Approximate constant memory usage for computation of reports (i.e., can't load the data all into memory at one time)
- Iterate exactly ONCE over the file/data
- Compute the following metrics and return a summary datastructure from your API
  - Total number of users processed
  - Mean age of all users
  - Median age of all users
  - Top 5 favorite colors
  - Total number of users processed with age greater than 21
  - Mean age of all users with age greater than 21
  - Median age of all users with age greater than 21
  - Top 5 favorite colors of user with age greater than 21

Reminder, the data/file can only be iterated over ONCE to compute all metrics and memory consumption remain approximately constant.

Test data is provided in "users.csv". However, in general, the datasource could be streamed from another source (database).

## Future Iteration and Discussion Points

- Could your API be extended to support searching over any field (e.g., user id, color) or fields?
- What is your recommended solution to communicate intermediate summaries? For example, after every 10000 records.
- How would we generalize this reporting API to other Record types, with different fields, such as a Car with VIN, brand, purchased at, etc...
- How would communicate errors with potentially malformed data from the IO source?
  
