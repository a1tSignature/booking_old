create table if not exists chats
(
    chat_id    SERIAL PRIMARY KEY,
    landlord_id    INTEGER,
    tenant_id INTEGER,
    CONSTRAINT user_roles_landlord_id_fk FOREIGN KEY (landlord_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT user_roles_tenant_fk FOREIGN KEY (tenant_id) REFERENCES users (user_id) ON DELETE CASCADE
);
create table if not exists chat_lines
(
    chat_id INTEGER,
    sent_by    VARCHAR(255),
    created_at TIMESTAMP,
    message VARCHAR(255),
    CONSTRAINT chat_lines_chat_id_fk FOREIGN KEY (chat_id) REFERENCES chats (chat_id) ON DELETE CASCADE
);
