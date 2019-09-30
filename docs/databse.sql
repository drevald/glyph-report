CREATE TABLE reports_tbl (
  id_col serial PRIMARY KEY,
  original_page_col bytea,
  reflowed_page_col bytea,
  glyphs_col bytea,
  created_col timestamp
);

COMMIT