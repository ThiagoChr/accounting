package br.accounting.domain.util.reader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class CSVFileReader<T> {

	private String filePath;
	private Path path;

	public CSVFileReader(String filePath) {
		this.filePath = Preconditions.checkNotNull(filePath,
				"The file path must be informed!");

		path = Paths.get(filePath);

		Preconditions
				.checkState(Files.exists(path), "The file does not exist!");
	}

	protected abstract T buildUpObject(String[] record,
			Map<String, Integer> header);

	public List<T> parseCSV() throws IOException {

		// create CSVReader object
		CSVReader reader = new CSVReader(new FileReader(filePath), ',');

		List<T> consolidatedList = Lists.newArrayList();

		// read line by line
		String[] record = null;

		// skip header row
		Map<String, Integer> header = buildHeader(reader.readNext());

		while ((record = reader.readNext()) != null) {

			T builtObject = buildUpObject(record, header);

			consolidatedList.add(builtObject);
		}

		reader.close();

		return consolidatedList;
	}

	private Map<String, Integer> buildHeader(String[] headerTokens) {

		Map<String, Integer> header = Maps.newHashMap();

		int position = 0;

		for (String token : headerTokens) {
			header.put(token, position++);
		}

		return header;
	}

	public String getFilePath() {
		return filePath;
	}

}
