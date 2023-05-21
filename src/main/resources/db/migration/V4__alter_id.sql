-- customer_account.account_id -> to customer_account.id
ALTER TABLE customer_account
RENAME COLUMN account_id TO id;

-- transaction.transaction_id -> to transaction.id
ALTER TABLE transaction
RENAME COLUMN transaction_id TO id;