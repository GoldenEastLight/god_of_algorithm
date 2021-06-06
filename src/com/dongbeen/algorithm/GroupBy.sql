SELECT BRANCH_ID, COUNT(BRANCH_ID) AS '계약 건수'
  FROM EMPLOYEES AS E LEFT OUTER JOIN SELLINGS AS S
    ON E.ID = S.EMPLOYEE_ID
 GROUP BY E.BRANCH_ID
 ORDER BY E.BRANCH_ID ASC;