# _{Band Tracker}_

#### _{Java Database}, {May 19 2016}_

#### By _**{Liam Ellis}**_

## Description

_{This is a java based web app for a sample band blog. It implements a many-to-many SQL data structure, and renders the App using Spark and Velocity}_

## Setup/Installation Requirements

CREATE DATABASE band_tracker;
\c band_tracker;
CREATE TABLE bands (id serial PRIMARY KEY, name varchar);
CREATE TABLE venues (id serial PRIMARY KEY, name varchar, city varchar);
CREATE DATABASE band_tracker_test WITH TEMPLATE band_tracker;

_{Clone the Repo}_

## Known Bugs

_{No known bugs}_

## Support and contact details

_{For issues or support contact me at liamhellis@gmail.com}_

## Technologies Used

_{Java, Spark, Velocity, SQL, HTML, CSS}_


Copyright (c) 2016 **_{Liam Ellis}_**