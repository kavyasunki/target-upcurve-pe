To create TABLES
```sql
Create table REGIONS (
    region_id int,
    region_name varchar(50) NOT NULL,
    PRIMARY KEY (region_id)
    );
```
```sql
create table COUNTRIES(
    country_id varchar(2) PRIMARY KEY,
    country_name varchar(50) NOT NULL,
    region_id int,
    FOREIGN KEY (region_id) REFERENCES REGIONS(region_id)
    );
```
```sql
create table LOCATIONS(
    location_id int PRIMARY KEY,
    street_address varchar(255),
    postal_code varchar(10),
    city varchar(50),
    state_province varchar(50),
    country_id varchar(2),
    FOREIGN KEY (country_id) REFERENCES COUNTRIES(country_id)
    );
```
```sql
create table DEPARTMENTS(
    department_id int PRIMARY KEY,
    department_name varchar(50),
    manager_id int,
    location_id int,
    FOREIGN KEY (location_id) REFERENCES LOCATIONS(location_id)
    );
```
```sql
create table JOBS(
    job_id varchar(15) PRIMARY KEY,
    job_title varchar(50),
    min_salary int,
    max_salary int
    );
```
```sql
create table EMPLOYEES(
    employee_id int PRIMARY KEY,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(100),
    phone_number varchar(50),
    hire_date date,
    job_id varchar(15),
    salary double,
    commission_pct double,
    manager_id int,
    department_id int,
    );
```
```sql
create table JOB_HISTORY(
     employee_id int,
     start_date date,
     end_date date,
     job_id varchar(15),
     department_id int,
     FOREIGN KEY (job_id) references JOBS(job_id),
     FOREIGN KEY (department_id) references DEPARTMENTS(department_id),
     PRIMARY KEY (employee_id,start_date)
     );
```
```sql
ALTER TABLE EMPLOYEES
    ADD constraint fk_jobid FOREIGN KEY(job_id) REFERENCES jobs(job_id);
ALTER TABLE EMPLOYEES
    ADD constraint fk_department_id FOREIGN KEY(department_id) REFERENCES departments(department_id);
```
TO INSERT VALUES INTO THE TABLES
```sql
INSERT INTO COUNTRIES VALUES
    ('IT', 'Italy', 1),
    ('JP', 'Japan', 3),
    ('US', 'United States of America', 2),
    .
    .
    .
     ('BE', 'Belgium', 1);
```

