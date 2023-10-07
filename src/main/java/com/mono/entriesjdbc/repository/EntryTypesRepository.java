package com.mono.entriesjdbc.repository;

import com.mono.entriesjdbc.model.EntryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EntryTypesRepository {

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EntryTypesRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final RowMapper<EntryType> entryTypeRowMapper = (rs, rowNum) -> {
        EntryType entryType = new EntryType();
        entryType.setType(rs.getString("type"));
        entryType.setDescription(rs.getString("description"));
        return entryType;
    };

    public List<EntryType> getAll() {
        String sql = "select type,description from test.entry_types order by type";
        return namedParameterJdbcTemplate.query(sql, entryTypeRowMapper);
    }

    public Optional<EntryType> getByType(String type) {
        String sql = "select type,description from test.entry_types where type= :type";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().
                addValue("type", type);
        List<EntryType> entryTypes = namedParameterJdbcTemplate.query(sql, sqlParameterSource, entryTypeRowMapper);
        return entryTypes.isEmpty() ? Optional.empty() : Optional.of(entryTypes.get(0));
    }
}
