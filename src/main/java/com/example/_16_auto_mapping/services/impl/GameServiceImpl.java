package com.example._16_auto_mapping.services.impl;

import com.example._16_auto_mapping.DTOS.DetailGameDTO;
import com.example._16_auto_mapping.DTOS.GameDTO;
import com.example._16_auto_mapping.entities.Game;
import com.example._16_auto_mapping.repositories.GameDao;
import com.example._16_auto_mapping.services.GameService;
import com.example._16_auto_mapping.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GameServiceImpl  implements GameService {


    private final GameDao gameDao;

    private final ValidatorUtil validatorUtil;

    private final StringBuilder sb;

    private final ModelMapper mapper;

    public GameServiceImpl(GameDao gameDao, ValidatorUtil validatorUtil, ModelMapper mapper) {
        this.gameDao = gameDao;
        this.validatorUtil = validatorUtil;
        this.mapper = mapper;
        sb = new StringBuilder();
    }

    @Override
    public String addGame(Game game) {
        sb.delete(0, sb.length());
        if (validatorUtil.isValid(game)){
            gameDao.saveAndFlush(game);
            sb.append(String.format("Added %s", game.getTitle()));
        }else {
            validatorUtil.violations(game).forEach(e-> sb.append(e.getMessage()).append(" "));
        }
        return sb.toString().trim();
    }

    @Override
    public String editGame(long id, List<String> values) {

        Optional<Game> game = gameDao.findById(id);
        if (game.isPresent()){

            for (int i = 1; i < values.size(); i++) {
                 String property = values.get(i).split("=")[0];
                 String valueOfProperty = values.get(i).split("=")[1];
              switch (property){
                  case "price":
                      BigDecimal newPrice = new BigDecimal(valueOfProperty);
                      game.get().setPrice(newPrice);
                      break;
                  case "title":
                      String title = valueOfProperty.toString();
                      game.get().setTitle(title);
                      break;
                  case "size":
                       double size = Double.parseDouble(valueOfProperty.toString());
                       game.get().setSize(size);
                      break;
                  case "trailer":
                      String trailer = valueOfProperty.toString();
                      game.get().setTrailer(trailer);
                      break;
                  case "thumbnailURL":
                      String thumbnailURl = valueOfProperty.toString();
                      game.get().setImageThumbnail(thumbnailURl);
                      break;
                  case"description":
                      String description = valueOfProperty.toString();
                      game.get().setDescription(description);
                      break;
                  case "releaseDate":
                      LocalDate localDate = LocalDate.parse(valueOfProperty.toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                      game.get().setReleaseDate(localDate);
                      break;
                  default:
                      System.out.printf("Property %s does not found", property);
                      break;
              }
            }
            gameDao.save(game.get());
            sb.append(String.format("Edited %s", game.get().getTitle()));
        }else {
            sb.append("Invalid id");
        }
        return sb.toString().trim();
    }

    @Override
    public String deleteGame(long id) {
        Optional<Game> gameToDelete = gameDao.findById(id);
        if (gameDao.deleteById(id)) {
            return String.format("Deleted %s", gameToDelete.get().getTitle());
        }
        return String.format("Game with id %d does not found", id);
    }

    @Override
    public void viewAllGames() {
        List<Game> games = gameDao.findAll();
        List<GameDTO> gamesDto = new ArrayList<>();


        for (Game game : games) {
            gamesDto.add(mapper.map(game, GameDTO.class));
        }
        for (GameDTO gameDto : gamesDto) {
            System.out.print(gameDto);
        }
    }

    @Override
    public String findGame(String title) {
      Optional<Game> game =  gameDao.findByTitle(title);
      if (game != null) {
          DetailGameDTO detailGameDTO = mapper.map(game.get(), DetailGameDTO.class);
          return detailGameDTO.toString();
      }
      return String.format("%s not found.", title);
    }
}
