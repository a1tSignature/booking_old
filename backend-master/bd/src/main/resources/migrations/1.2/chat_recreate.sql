DROP TABLE IF EXISTS chats cascade;
CREATE TABLE IF NOT EXISTS chats
(
    chat_id    SERIAL PRIMARY KEY,
    landlord_id    INTEGER,
    tenant_id INTEGER,
    CONSTRAINT user_roles_landlord_id_fk FOREIGN KEY (landlord_id) REFERENCES minibooking.public.users (user_id) ON DELETE CASCADE,
    CONSTRAINT user_roles_tenant_fk FOREIGN KEY (tenant_id) REFERENCES minibooking.public.users (user_id) ON DELETE CASCADE
);
