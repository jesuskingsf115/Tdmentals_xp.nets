ALTER TABLE email_notifications
    ALTER COLUMN sent_at DROP NOT NULL;

ALTER TABLE email_notifications
    ALTER COLUMN status SET DEFAULT 'PENDING';
