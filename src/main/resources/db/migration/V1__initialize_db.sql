CREATE TYPE "car_color" AS ENUM (
  'white',
  'black',
  'brown',
  'yellow',
  'red',
  'blue',
  'silver'
);

CREATE TYPE "service_class" AS ENUM (
  'ONE',
  'TWO',
  'THREE',
  'FOUR',
  'FIVE'
);

CREATE TABLE "role" (
  "id" uuid PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "user" (
  "id" uuid PRIMARY KEY,
  "first_name" varchar,
  "last_name" varchar,
  "middle_name" varchar,
  "role_id" uuid,
  "created_at" timestamp,
  "phone" integer,
  "email" varchar
);

CREATE TABLE "password" (
  "id" uuid PRIMARY KEY,
  "user_id" uuid UNIQUE,
  "value" varchar,
  "created_at" timestamp,
  "active" boolean
);

CREATE TABLE "tour" (
  "id" uuid PRIMARY KEY,
  "departure_flight_id" uuid NOT NULL,
  "arrival_flight_id" uuid NOT NULL,
  "transfer_to_hotel_id" uuid NOT NULL,
  "transfer_from_hotel_id" uuid NOT NULL,
  "description" text NOT NULL,
  "price" bigint NOT NULL,
  "hotel_room_id" uuid NOT NULL,
  "selected_food_option_id" uuid NOT NULL
);

CREATE TABLE "flight" (
  "id" uuid PRIMARY KEY,
  "departure_airport" varchar NOT NULL,
  "arrival_airport" varchar NOT NULL,
  "departure_date_time" timestamp NOT NULL,
  "arrival_date_time" timestamp NOT NULL
);

CREATE TABLE "transfer" (
  "id" uuid PRIMARY KEY,
  "name" varchar NOT NULL,
  "car_id" uuid,
  "departure_coordinates" varchar NOT NULL,
  "arrival_coordinates" varchar NOT NULL,
  "price" bigint NOT NULL,
  "departure_date_time" timestamp NOT NULL,
  "arrival_date_time" timestamp NOT NULL,
  "is_guide_included" boolean NOT NULL
);

CREATE TABLE "car" (
  "id" uuid PRIMARY KEY,
  "mark_id" uuid NOT NULL,
  "model_id" uuid NOT NULL,
  "plate_number" varchar UNIQUE NOT NULL,
  "color" car_color
);

CREATE TABLE "car_mark" (
  "id" uuid PRIMARY KEY,
  "name" varchar UNIQUE NOT NULL
);

CREATE TABLE "car_model" (
  "id" uuid PRIMARY KEY,
  "name" varchar UNIQUE NOT NULL
);

CREATE TABLE "hotel" (
  "id" uuid PRIMARY KEY,
  "name" varchar UNIQUE NOT NULL,
  "address" varchar NOT NULL,
  "class" service_class NOT NULL,
  "is_guide_included" boolean NOT NULL
);

CREATE TABLE "room" (
  "id" uuid PRIMARY KEY,
  "name" varchar NOT NULL,
  "class" service_class NOT NULL,
  "price_per_night" bigint NOT NULL,
  "hotel_id" uuid NOT NULL
);

CREATE TABLE "room_photo" (
  "id" uuid PRIMARY KEY,
  "room_id" uuid NOT NULL,
  "url" text UNIQUE NOT NULL
);

CREATE TABLE "hotel_photo" (
  "id" uuid PRIMARY KEY,
  "hotel_id" uuid NOT NULL,
  "url" text UNIQUE NOT NULL
);

CREATE TABLE "food_option" (
  "id" uuid PRIMARY KEY,
  "name" varchar UNIQUE NOT NULL,
  "price" bigint
);

CREATE TABLE "flight_food_option" (
  "id" uuid PRIMARY KEY,
  "name" varchar UNIQUE NOT NULL,
  "price" bigint
);

CREATE TABLE "tour_feedback" (
  "id" uuid PRIMARY KEY,
  "tour_id" uuid NOT NULL,
  "title" varchar NOT NULL,
  "body" text NOT NULL,
  "user_id" uuid,
  "is_moderated" boolean NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE TABLE "hotel_feedback" (
  "id" uuid PRIMARY KEY,
  "hotel_id" uuid NOT NULL,
  "title" varchar NOT NULL,
  "body" text NOT NULL,
  "user_id" uuid,
  "is_moderated" boolean NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE TABLE "booking" (
  "id" uuid PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "tour_id" uuid NOT NULL
);

CREATE TABLE "booking_approval" (
  "id" uuid PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "booking_id" uuid UNIQUE
);

CREATE TABLE "payment" (
  "id" uuid PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "tour_id" uuid NOT NULL
);

CREATE TABLE "payment_approval" (
  "id" uuid PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "payment_id" uuid UNIQUE
);

COMMENT ON COLUMN "tour_feedback"."body" IS 'Content of the review';

COMMENT ON COLUMN "hotel_feedback"."body" IS 'Content of the review';

ALTER TABLE "tour" ADD FOREIGN KEY ("hotel_room_id") REFERENCES "room" ("id");

ALTER TABLE "tour" ADD FOREIGN KEY ("selected_food_option_id") REFERENCES "food_option" ("id");

ALTER TABLE "tour" ADD FOREIGN KEY ("departure_flight_id") REFERENCES "flight" ("id");

ALTER TABLE "tour" ADD FOREIGN KEY ("arrival_flight_id") REFERENCES "flight" ("id");

ALTER TABLE "tour" ADD FOREIGN KEY ("transfer_to_hotel_id") REFERENCES "transfer" ("id");

ALTER TABLE "tour" ADD FOREIGN KEY ("transfer_from_hotel_id") REFERENCES "transfer" ("id");

ALTER TABLE "transfer" ADD FOREIGN KEY ("car_id") REFERENCES "car" ("id");

ALTER TABLE "car" ADD FOREIGN KEY ("mark_id") REFERENCES "car_mark" ("id");

ALTER TABLE "car" ADD FOREIGN KEY ("model_id") REFERENCES "car_model" ("id");

ALTER TABLE "room" ADD FOREIGN KEY ("hotel_id") REFERENCES "hotel" ("id");

ALTER TABLE "room_photo" ADD FOREIGN KEY ("room_id") REFERENCES "room" ("id");

ALTER TABLE "hotel_photo" ADD FOREIGN KEY ("hotel_id") REFERENCES "hotel" ("id");

ALTER TABLE "tour_feedback" ADD FOREIGN KEY ("tour_id") REFERENCES "tour" ("id");

ALTER TABLE "hotel_feedback" ADD FOREIGN KEY ("hotel_id") REFERENCES "hotel" ("id");

ALTER TABLE "user" ADD FOREIGN KEY ("id") REFERENCES "password" ("user_id");

ALTER TABLE "user" ADD FOREIGN KEY ("role_id") REFERENCES "role" ("id");

ALTER TABLE "tour_feedback" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "booking" ADD FOREIGN KEY ("tour_id") REFERENCES "tour" ("id");

ALTER TABLE "booking" ADD FOREIGN KEY ("id") REFERENCES "booking_approval" ("booking_id");

ALTER TABLE "payment" ADD FOREIGN KEY ("tour_id") REFERENCES "booking" ("id");

ALTER TABLE "payment" ADD FOREIGN KEY ("id") REFERENCES "payment_approval" ("payment_id");
